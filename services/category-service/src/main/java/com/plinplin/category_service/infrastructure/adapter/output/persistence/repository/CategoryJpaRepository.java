package com.plinplin.category_service.infrastructure.adapter.output.persistence.repository;

import com.plinplin.category_service.infrastructure.adapter.output.persistence.entity.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryJpaRepository extends JpaRepository<CategoryEntity, Long> {
}
