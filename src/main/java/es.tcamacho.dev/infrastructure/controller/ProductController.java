package es.tcamacho.dev.infrastructure.controller;

import es.tcamacho.dev.application.ProductService;
import es.tcamacho.dev.domain.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService service;

    @GetMapping("/{productId}/similar")
    public ResponseEntity<List<Product>> getSimilarProducts(@PathVariable String productId) {
        try {
            List<Product> products = service.execute(productId);
            return ResponseEntity.ok(products);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build(); // Fallback genérico si algo va mal
        }
    }
}
