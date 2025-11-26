package com.plinplin.category_service.infrastructure.adapter.input.rest.mapper.impl;

import com.plinplin.category_service.domain.model.Category;
import com.plinplin.category_service.infrastructure.adapter.input.rest.dto.CategoryRequestDto;
import com.plinplin.category_service.infrastructure.adapter.input.rest.dto.CategoryResponseDto;
import com.plinplin.category_service.infrastructure.adapter.input.rest.dto.CategoryUpdateDto;
import com.plinplin.category_service.infrastructure.adapter.input.rest.mapper.CategoryRestMapper;
import org.springframework.stereotype.Component;

@Component
public class CategoryRestMapperImpl implements CategoryRestMapper {
    @Override
    public Category requestToDomain(CategoryRequestDto categoryRequestDto) {
        return Category.builder()
                .name(categoryRequestDto.name())
                .description(categoryRequestDto.description())
                .build();
    }

    @Override
    public Category updateToDomain(CategoryUpdateDto categoryUpdateDto) {
        return Category.builder()
                .id(categoryUpdateDto.id())
                .name(categoryUpdateDto.name())
                .description(categoryUpdateDto.description())
                .build();
    }

    @Override
    public CategoryResponseDto toDto(Category category) {
        return new CategoryResponseDto(
                category.getId(),
                category.getName(),
                category.getDescription()
        );
    }
}
