package com.plinplin.product_service.application.input;

import com.plinplin.product_service.domain.model.Product;

import java.util.List;
import java.util.Optional;

public interface ProductServicePort {
    Product createProduct(Product product);

    Optional<Product> getProductById(Long id);

    List<Product> getAllProducts();

    void updateProduct(Product product);

    void deleteProduct(Long id);

    List<Product> findProductsByCategoryId(Long categoryId);

    void updateStock(Long id, Integer quantity);
}
