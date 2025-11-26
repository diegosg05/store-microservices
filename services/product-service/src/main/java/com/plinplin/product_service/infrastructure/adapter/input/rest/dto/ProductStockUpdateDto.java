package com.plinplin.product_service.infrastructure.adapter.input.rest.dto;

public record ProductStockUpdateDto(
        Long id,
        Integer quantity
) {
}
