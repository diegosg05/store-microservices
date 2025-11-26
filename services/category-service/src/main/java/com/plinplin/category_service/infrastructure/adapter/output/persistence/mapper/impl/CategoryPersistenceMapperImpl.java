package com.plinplin.category_service.infrastructure.adapter.output.persistence.mapper.impl;

import com.plinplin.category_service.domain.model.Category;
import com.plinplin.category_service.infrastructure.adapter.output.persistence.entity.CategoryEntity;
import com.plinplin.category_service.infrastructure.adapter.output.persistence.mapper.CategoryPersistenceMapper;
import org.springframework.stereotype.Component;

@Component
public class CategoryPersistenceMapperImpl implements CategoryPersistenceMapper {
    @Override
    public Category toDomain(CategoryEntity categoryEntity) {
        return Category.builder()
                .id(categoryEntity.getId())
                .name(categoryEntity.getName())
                .description(categoryEntity.getDescription())
                .build();
    }

    @Override
    public CategoryEntity toEntity(Category category) {
        return CategoryEntity.builder()
                .id(category.getId())
                .name(category.getName())
                .description(category.getDescription())
                .build();
    }
}
