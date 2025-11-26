package com.plinplin.product_service.infrastructure.adapter.input.rest.mapper.impl;

import com.plinplin.product_service.domain.model.Category;
import com.plinplin.product_service.domain.model.Product;
import com.plinplin.product_service.infrastructure.adapter.input.rest.dto.CategoryResponseDto;
import com.plinplin.product_service.infrastructure.adapter.input.rest.dto.ProductRequestDto;
import com.plinplin.product_service.infrastructure.adapter.input.rest.dto.ProductResponseDto;
import com.plinplin.product_service.infrastructure.adapter.input.rest.dto.ProductUpdateDto;
import com.plinplin.product_service.infrastructure.adapter.input.rest.mapper.ProductRestMapper;
import org.springframework.stereotype.Component;

@Component
public class ProductRestMapperImpl implements ProductRestMapper {
    @Override
    public Product toDomain(ProductRequestDto productRequestDto) {
        return Product.builder()
                .name(productRequestDto.name())
                .price(productRequestDto.price())
                .stock(productRequestDto.stock())
                .description(productRequestDto.description())
                .status("CREATED")
                .categoryId(productRequestDto.categoryId())
                .build();
    }

    @Override
    public Product UpdateToDomain(ProductUpdateDto productUpdateDto) {
        return Product.builder()
                .id(productUpdateDto.id())
                .name(productUpdateDto.name())
                .price(productUpdateDto.price())
                .stock(productUpdateDto.stock())
                .description(productUpdateDto.description())
                .status(productUpdateDto.status())
                .categoryId(productUpdateDto.categoryId())
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

    @Override
    public ProductResponseDto toDto(Product product, CategoryResponseDto category) {
        return new ProductResponseDto(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getStock(),
                product.getPrice(),
                category,
                product.getStatus()
        );
    }
}
