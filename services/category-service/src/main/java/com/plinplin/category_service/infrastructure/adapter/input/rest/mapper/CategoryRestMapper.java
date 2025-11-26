package com.plinplin.category_service.infrastructure.adapter.input.rest.mapper;

import com.plinplin.category_service.domain.model.Category;
import com.plinplin.category_service.infrastructure.adapter.input.rest.dto.CategoryRequestDto;
import com.plinplin.category_service.infrastructure.adapter.input.rest.dto.CategoryResponseDto;
import com.plinplin.category_service.infrastructure.adapter.input.rest.dto.CategoryUpdateDto;

public interface CategoryRestMapper {
    Category requestToDomain(CategoryRequestDto categoryRequestDto);
    Category updateToDomain(CategoryUpdateDto categoryUpdateDto);
    CategoryResponseDto toDto(Category category);
}
