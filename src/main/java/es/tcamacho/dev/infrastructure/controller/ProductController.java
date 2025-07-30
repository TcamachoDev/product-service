package es.tcamacho.dev.infrastructure.controller;

import es.tcamacho.dev.application.ProductService;
import es.tcamacho.dev.domain.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

import static es.tcamacho.dev.config.Constants.PRODUCT_ID_SIMILAR;
import static es.tcamacho.dev.config.Constants.PRODUCT;

@RestController
@RequestMapping(PRODUCT)
@RequiredArgsConstructor
public class ProductController {

    private final ProductService service;

    @GetMapping(PRODUCT_ID_SIMILAR)
    public Flux<Product> getSimilarProducts(@PathVariable String productId) {
        return service.getSimilarProducts(productId);
    }
}
