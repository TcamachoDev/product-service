package es.tcamacho.dev.application;

import es.tcamacho.dev.domain.Product;
import es.tcamacho.dev.infrastructure.client.ProductClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductClient productClient;

    public Flux<Product> getSimilarProducts(String productId) {
        return productClient.getSimilarProductIds(productId)
                .flatMapMany(Flux::fromIterable)
                .flatMap(id -> productClient.getProductById(id)
                        .onErrorResume(ex -> {
                            log.warn("Could not retrieve product with id: {}: {}", id, ex.getMessage());
                            return Mono.empty();
                        })
                );
    }
}
