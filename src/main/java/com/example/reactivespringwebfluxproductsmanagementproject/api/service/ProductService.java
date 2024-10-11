package com.example.reactivespringwebfluxproductsmanagementproject.api.service;

import com.example.reactivespringwebfluxproductsmanagementproject.api.entities.Product;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ProductService {

 Flux<Product> getAllProducts();
 Mono<Product> getProductById(Long id);
 Mono<Product> saveProduct(Product product);
 Mono<Void> deleteProduct(Long id);



}
