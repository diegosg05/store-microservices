package com.plinplin.product_service.infrastructure.adapter.input.rest.dto;

public record ProductUpdateDto(
        Long id,
        String name,
        String description,
        Integer stock,
        Double price,
        Long categoryId,
        String status
) {
}
