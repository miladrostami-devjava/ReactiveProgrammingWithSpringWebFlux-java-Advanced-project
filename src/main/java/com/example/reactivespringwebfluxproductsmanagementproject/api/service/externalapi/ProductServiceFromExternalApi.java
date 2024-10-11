package com.example.reactivespringwebfluxproductsmanagementproject.api.service.externalapi;


import com.example.reactivespringwebfluxproductsmanagementproject.api.entities.Product;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

/** this service is for connection with external services , for example whatsapp api in our Applications
 * with use as WebClient framework */

@Service
public class ProductServiceFromExternalApi {

    private final WebClient webClient;

    public ProductServiceFromExternalApi(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("https://wa.me/+001-(555)1234567").build();
    }



public Mono<Product> fetchProductFromExternalApi(Long id) {
return webClient.get()
        .uri("/api/v2/reactive/{id}",id)
        .retrieve()
        .bodyToMono(Product.class);
}
}
