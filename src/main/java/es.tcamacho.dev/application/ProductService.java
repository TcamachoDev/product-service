package es.tcamacho.dev.application;

import es.tcamacho.dev.domain.Product;
import es.tcamacho.dev.infrastructure.client.ProductClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductClient productClient;

    public List<Product> execute(String productId) {
        List<String> similarIds = productClient.getSimilarProductIds(productId);

        return similarIds.stream()
                .map(id -> {
                    try {
                        return productClient.getProductById(id);
                    } catch (Exception e) {
                        // Puedes registrar el error si algún producto no se puede obtener
                        return null;
                    }
                })
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }
}
