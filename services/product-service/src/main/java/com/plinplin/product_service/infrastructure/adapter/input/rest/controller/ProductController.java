package com.plinplin.product_service.infrastructure.adapter.input.rest.controller;

import com.plinplin.product_service.application.input.ProductServicePort;
import com.plinplin.product_service.application.output.CategoryClient;
import com.plinplin.product_service.infrastructure.adapter.input.rest.dto.ProductRequestDto;
import com.plinplin.product_service.infrastructure.adapter.input.rest.dto.ProductResponseDto;
import com.plinplin.product_service.infrastructure.adapter.input.rest.dto.ProductStockUpdateDto;
import com.plinplin.product_service.infrastructure.adapter.input.rest.dto.ProductUpdateDto;
import com.plinplin.product_service.infrastructure.adapter.input.rest.mapper.ProductRestMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductServicePort service;
    private final ProductRestMapper mapper;
    private final CategoryClient categoryClient;

    @GetMapping
    public ResponseEntity<List<ProductResponseDto>> findAll() {
        var products = service.getAllProducts().stream()
                .map(product -> {
                    var category = categoryClient.getCategory(product.getCategoryId());
                    return mapper.toDto(product, mapper.toDto(category));
                })
                .toList();

        return ResponseEntity.ok(products);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponseDto> findById(@PathVariable Long id) {
        var optionalProduct = service.getProductById(id);

        return optionalProduct
                .map(product -> {
                    var category = categoryClient.getCategory(product.getId());
                    return ResponseEntity.ok(mapper.toDto(product, mapper.toDto(category)));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<ProductResponseDto> save(@RequestBody ProductRequestDto newProduct) {
        var category = categoryClient.getCategory(newProduct.categoryId());
        var productSaved = service.createProduct(mapper.toDomain(newProduct));

        return ResponseEntity.created(
                URI.create("/products/".concat(productSaved.getId().toString()))
        ).body(mapper.toDto(productSaved, mapper.toDto(category)));
    }

    @PutMapping
    public ResponseEntity<Void> update(@RequestBody ProductUpdateDto updateProduct) {
        categoryClient.getCategory(updateProduct.categoryId());
        service.updateProduct(mapper.UpdateToDomain(updateProduct));

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {

        if (service.getProductById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        service.deleteProduct(id);

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/category/{id}")
    public ResponseEntity<List<ProductResponseDto>> findAllByCategoryId(@PathVariable Long id) {
        var category = categoryClient.getCategory(id);

        var products = service.findProductsByCategoryId(id).stream()
                .map(product ->
                        mapper.toDto(product, mapper.toDto(category))
                )
                .toList();

        return ResponseEntity.ok(products);
    }

    @PatchMapping
    public ResponseEntity<Void> updateProductStock(@RequestBody ProductStockUpdateDto stockUpdate) {

        if (service.getProductById(stockUpdate.id()).isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        service.updateStock(stockUpdate.id(), stockUpdate.quantity());

        return ResponseEntity.noContent().build();
    }
}
