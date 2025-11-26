package com.plinplin.product_service.infrastructure.adapter.input.rest.dto;

public record ProductRequestDto(
        String name,
        String description,
        Integer stock,
        Double price,
        Long categoryId
) {

}
