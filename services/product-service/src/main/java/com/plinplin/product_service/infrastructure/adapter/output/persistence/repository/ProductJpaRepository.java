package com.plinplin.product_service.infrastructure.adapter.output.persistence.repository;

import com.plinplin.product_service.infrastructure.adapter.output.persistence.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductJpaRepository extends JpaRepository<ProductEntity, Long> {
    List<ProductEntity> findByCategoryId(Long categoryId);
}
