package com.plinplin.product_service.infrastructure.adapter.output.persistence;

import com.plinplin.product_service.application.output.ProductRepositoryPort;
import com.plinplin.product_service.domain.model.Product;
import com.plinplin.product_service.infrastructure.adapter.output.persistence.mapper.ProductPersistenceMapper;
import com.plinplin.product_service.infrastructure.adapter.output.persistence.repository.ProductJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class ProductPersistenceAdapter implements ProductRepositoryPort {

    private final ProductJpaRepository repository;
    private final ProductPersistenceMapper mapper;

    @Override
    public Product save(Product product) {
        var productSaved = repository.save(mapper.toEntity(product));
        return mapper.toDomain(productSaved);
    }

    @Override
    public Optional<Product> findById(Long id) {
        return repository.findById(id)
                .map(mapper::toDomain);
    }

    @Override
    public List<Product> findAll() {
        return repository.findAll().stream()
                .map(mapper::toDomain)
                .toList();
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    @Override
    public List<Product> findByCategoryId(Long categoryId) {
        return repository.findByCategoryId(categoryId).stream()
                .map(mapper::toDomain)
                .toList();
    }
}
