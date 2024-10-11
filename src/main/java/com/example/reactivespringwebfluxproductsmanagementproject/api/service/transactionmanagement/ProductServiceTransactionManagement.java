package com.example.reactivespringwebfluxproductsmanagementproject.api.service.transactionmanagement;


import com.example.reactivespringwebfluxproductsmanagementproject.api.entities.Product;
import com.example.reactivespringwebfluxproductsmanagementproject.api.repo.ProductRepo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

import javax.sound.sampled.Port;

/**Reactive Transaction Management*/
@Service
public class ProductServiceTransactionManagement {
    private final ProductRepo productRepo;

    public ProductServiceTransactionManagement(ProductRepo productRepo) {
        this.productRepo = productRepo;
    }

    @Transactional
    public Mono<Product> createAndLogProduct(Product product){
        productRepo.save(product)
                .then(logProductCreation(product));

    }

    private Mono<Void> logProductCreation(Product product) {
        return Mono.empty();
    }


}
