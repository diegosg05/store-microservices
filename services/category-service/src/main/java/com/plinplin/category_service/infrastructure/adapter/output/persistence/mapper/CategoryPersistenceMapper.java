package com.plinplin.category_service.infrastructure.adapter.output.persistence.mapper;

import com.plinplin.category_service.domain.model.Category;
import com.plinplin.category_service.infrastructure.adapter.output.persistence.entity.CategoryEntity;

public interface CategoryPersistenceMapper {

    Category toDomain(CategoryEntity categoryEntity);
    CategoryEntity toEntity(Category category);
}
