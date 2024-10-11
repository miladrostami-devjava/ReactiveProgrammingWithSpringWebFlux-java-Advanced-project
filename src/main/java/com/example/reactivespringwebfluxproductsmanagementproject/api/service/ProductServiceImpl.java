package com.example.reactivespringwebfluxproductsmanagementproject.api.service;

import com.example.reactivespringwebfluxproductsmanagementproject.api.entities.Product;
import com.example.reactivespringwebfluxproductsmanagementproject.api.repo.ProductRepo;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ProductServiceImpl implements ProductService{
    private final ProductRepo productRepo;

    public ProductServiceImpl(ProductRepo productRepo) {
        this.productRepo = productRepo;
    }


    @Override
    public Flux<Product> getAllProducts() {
        return productRepo.findAll();
    }

    @Override
    public Mono<Product> getProductById(Long id) {
        return productRepo.findById(id);
    }

    @Override
    public Mono<Product> saveProduct(Product product) {
        return productRepo.save(product);
    }


    @Override
    public Mono<Void> deleteProduct(Long id) {
        return productRepo.deleteById(id);
    }
}
