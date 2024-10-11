package com.example.reactivespringwebfluxproductsmanagementproject.api.repo;

import com.example.reactivespringwebfluxproductsmanagementproject.api.entities.Product;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface ProductRepo extends ReactiveCrudRepository<Product,Long> {
}
