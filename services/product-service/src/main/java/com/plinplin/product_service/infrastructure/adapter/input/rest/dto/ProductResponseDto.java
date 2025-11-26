package com.plinplin.product_service.infrastructure.adapter.input.rest.dto;

public record ProductResponseDto(
        Long id,
        String name,
        String description,
        Integer stock,
        Double price,
        CategoryResponseDto category,
        String status
) {
}
