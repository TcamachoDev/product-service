package es.tcamacho.dev.infrastructure.client;

import es.tcamacho.dev.domain.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ProductClient {

    private final WebClient webClient;

    public List<String> getSimilarProductIds(String productId) {
        return webClient
                .get()
                .uri("/product/{id}/similarids", productId)
                .retrieve()
                .bodyToMono(List.class)
                .block(); // bloqueamos para mantenerlo simple (aunque sea reactivo)
    }

    public Product getProductById(String productId) {
        return webClient
                .get()
                .uri("/product/{id}", productId)
                .retrieve()
                .bodyToMono(Product.class)
                .block();
    }
}
