package com.example.reactivespringwebfluxproductsmanagementproject.api.service.servicekafka;


import com.example.reactivespringwebfluxproductsmanagementproject.api.entities.Product;
import com.example.reactivespringwebfluxproductsmanagementproject.api.repo.ProductRepo;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class ProductServiceKafka {

    private final KafkaTemplate<String,Product> kafkaTemplate;
    private final ProductRepo productRepo;


    public ProductServiceKafka(KafkaTemplate<String,Product> kafkaTemplate, ProductRepo productRepo) {
        this.kafkaTemplate = kafkaTemplate;
        this.productRepo = productRepo;
    }


    /**send message and save the product*/
    public Mono<Product> createProductWithKafkaService(Product product){
        return productRepo.save(product)
                .doOnSuccess(product_prod -> kafkaTemplate.send("product-topic",product_prod));
    }



}
