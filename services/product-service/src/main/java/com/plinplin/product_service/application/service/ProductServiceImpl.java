package com.plinplin.product_service.application.service;

import com.plinplin.product_service.application.input.ProductServicePort;
import com.plinplin.product_service.application.output.ProductRepositoryPort;
import com.plinplin.product_service.domain.model.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductServicePort {

    private final ProductRepositoryPort productRepositoryPort;

    @Override
    public Product createProduct(Product product) {
        return productRepositoryPort.save(product);
    }

    @Override
    public Optional<Product> getProductById(Long id) {
        return productRepositoryPort.findById(id);
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepositoryPort.findAll();
    }

    @Override
    public void updateProduct(Product product) {
        productRepositoryPort.save(product);
    }

    @Override
    public void deleteProduct(Long id) {
        productRepositoryPort.deleteById(id);
    }

    @Override
    public List<Product> findProductsByCategoryId(Long categoryId) {
        return productRepositoryPort.findByCategoryId(categoryId);
    }

    @Override
    public void updateStock(Long id, Integer quantity) {
        Product existing = productRepositoryPort.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

        int newStock = existing.getStock() + quantity;

        if (newStock < 0) {
            throw new RuntimeException("El stock no puede ser negativo");
        }

        existing.setStock(newStock);

        productRepositoryPort.save(existing);
    }
}
