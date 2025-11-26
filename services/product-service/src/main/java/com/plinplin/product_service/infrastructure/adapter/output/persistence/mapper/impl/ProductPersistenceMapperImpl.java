package com.plinplin.product_service.infrastructure.adapter.output.persistence.mapper.impl;

import com.plinplin.product_service.domain.model.Product;
import com.plinplin.product_service.infrastructure.adapter.output.persistence.entity.ProductEntity;
import com.plinplin.product_service.infrastructure.adapter.output.persistence.mapper.ProductPersistenceMapper;
import org.springframework.stereotype.Component;

@Component
public class ProductPersistenceMapperImpl implements ProductPersistenceMapper {
    @Override
    public Product toDomain(ProductEntity productEntity) {
        return Product.builder()
                .id(productEntity.getId())
                .stock(productEntity.getStock())
                .categoryId(productEntity.getCategoryId())
                .price(productEntity.getPrice())
                .description(productEntity.getDescription())
                .name(productEntity.getName())
                .status(productEntity.getStatus())
                .build();
    }

    @Override
    public ProductEntity toEntity(Product product) {
        return ProductEntity.builder()
                .status(product.getStatus())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .categoryId(product.getCategoryId())
                .stock(product.getStock())
                .id(product.getId())
                .build();
    }
}
