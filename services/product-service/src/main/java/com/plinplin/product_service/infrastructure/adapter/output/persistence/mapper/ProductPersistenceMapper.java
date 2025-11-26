package com.plinplin.product_service.infrastructure.adapter.output.persistence.mapper;

import com.plinplin.product_service.domain.model.Product;
import com.plinplin.product_service.infrastructure.adapter.output.persistence.entity.ProductEntity;

public interface ProductPersistenceMapper {
    Product toDomain(ProductEntity productEntity);
    ProductEntity toEntity(Product product);
}
