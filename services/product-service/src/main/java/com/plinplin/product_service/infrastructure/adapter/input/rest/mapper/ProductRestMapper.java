package com.plinplin.product_service.infrastructure.adapter.input.rest.mapper;

import com.plinplin.product_service.domain.model.Category;
import com.plinplin.product_service.domain.model.Product;
import com.plinplin.product_service.infrastructure.adapter.input.rest.dto.CategoryResponseDto;
import com.plinplin.product_service.infrastructure.adapter.input.rest.dto.ProductRequestDto;
import com.plinplin.product_service.infrastructure.adapter.input.rest.dto.ProductResponseDto;
import com.plinplin.product_service.infrastructure.adapter.input.rest.dto.ProductUpdateDto;

public interface ProductRestMapper {
    Product toDomain(ProductRequestDto productRequestDto);

    Product UpdateToDomain(ProductUpdateDto productUpdateDto);

    CategoryResponseDto toDto(Category category);

    ProductResponseDto toDto(Product product, CategoryResponseDto category);
}
