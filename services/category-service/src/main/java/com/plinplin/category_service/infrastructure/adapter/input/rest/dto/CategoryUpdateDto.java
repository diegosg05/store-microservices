package com.plinplin.category_service.infrastructure.adapter.input.rest.dto;

public record CategoryUpdateDto(
        Long id,
        String name,
        String description
) {
}
