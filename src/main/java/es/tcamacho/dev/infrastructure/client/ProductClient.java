package es.tcamacho.dev.infrastructure.client;

import es.tcamacho.dev.config.exceptions.exception.ClientRequestException;
import es.tcamacho.dev.config.exceptions.exception.ProductNotFoundException;
import es.tcamacho.dev.domain.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.List;

import static es.tcamacho.dev.config.Constants.*;

@Component
@RequiredArgsConstructor
public class ProductClient {

    private final WebClient webClient;

    public Mono<List<String>> getSimilarProductIds(String productId) {
        return webClient
                .get()
                .uri(PRODUCT_ID_SIMILARIDS, productId)
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError,
                        response -> Mono.error(new ProductNotFoundException(productId)))
                .onStatus(HttpStatusCode::is5xxServerError,
                        this::handleServerError)
                .bodyToMono(new ParameterizedTypeReference<List<String>>() {})
                .timeout(Duration.ofSeconds(3))
                .retry(1)
                .onErrorMap(ex -> new ClientRequestException(ERROR_RETRIEVING_PRODUCT + productId, ex));
    }

    public Mono<Product> getProductById(String productId) {
        return webClient
                .get()
                .uri(PRODUCT_ID, productId)
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError,
                        response -> Mono.error(new ProductNotFoundException(productId)))
                .onStatus(HttpStatusCode::is5xxServerError,
                        this::handleServerError)
                .bodyToMono(Product.class)
                .timeout(Duration.ofSeconds(3))
                .retry(1)
                .onErrorMap(ex -> new ClientRequestException(ERROR_RETRIEVING_PRODUCT + productId, ex));
    }

    private Mono<? extends Throwable> handleServerError(ClientResponse response) {
        return response.bodyToMono(String.class)
                .flatMap(body -> Mono.error(new ClientRequestException(ERROR_FROM_DOWNSTREAM_SERVICE + body)));
    }
}
