package com.plinplin.product_service.application.output;

import com.plinplin.product_service.domain.model.Product;

import java.util.List;
import java.util.Optional;

public interface ProductRepositoryPort {
    Product save(Product product);
    Optional<Product> findById(Long id);
    List<Product> findAll();
    void deleteById(Long id);
    List<Product> findByCategoryId(Long categoryId);
}
