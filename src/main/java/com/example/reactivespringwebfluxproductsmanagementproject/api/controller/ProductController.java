package com.example.reactivespringwebfluxproductsmanagementproject.api.controller;

import com.example.reactivespringwebfluxproductsmanagementproject.api.entities.Product;
import com.example.reactivespringwebfluxproductsmanagementproject.api.service.ProductService;
import com.example.reactivespringwebfluxproductsmanagementproject.api.service.ProductServiceImpl;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

@RestController
@RequestMapping("/api/v2/reactive/products")
public class ProductController {

    private final ProductService productService;


    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public Flux<Product> getAllProductsNormal(){
        return productService.getAllProducts();
    }


    /** backpressure handling in spring webflux*/
    @GetMapping
    public Flux<Product> getAllProducts(){
        return productService.getAllProducts()
                .limitRate(10) // backpressure handling in spring webflux
                .onBackpressureBuffer();
    }


    /**Reactive Streaming */
    @GetMapping
    public Flux<Product> streamAllProducts(){
        return productService.getAllProducts()
                .delayElements(Duration.ofSeconds(1));
    }








    @GetMapping("/{id}")
    public Mono<Product> getProductById(@PathVariable Long id){
        return productService.getProductById(id);
    }


    // Handling Errors in Reactive Streams
    @GetMapping("/{id}")
    public Mono<Product> getProductByIdWithErrorManagement(@PathVariable Long id){
        return productService.getProductById(id)
                .onErrorResume(e -> {
                    return Mono.just(new Product(0l,"unknown product ",0.0));
                });
    }




    @PostMapping
    public Mono<Product> createProduct(@RequestBody Product product){
        return productService.saveProduct(product);
    }

    @PutMapping("/{id}")
    public Mono<Product> updateProduct(@PathVariable Long id,@RequestBody Product product){
        return productService.getProductById(id)
                .flatMap(exitstingProduct -> {
                   exitstingProduct.setName(product.getName());
                   exitstingProduct.setPrice(product.getPrice());
                   return productService.saveProduct(exitstingProduct);
                });
    }

    @DeleteMapping("/{id}")
    public Mono<Void> deleteProduct(@PathVariable Long id){
        return productService.deleteProduct(id);
    }

}
