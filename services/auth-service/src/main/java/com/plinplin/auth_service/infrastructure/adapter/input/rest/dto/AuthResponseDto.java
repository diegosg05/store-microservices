package com.plinplin.auth_service.infrastructure.adapter.input.rest.dto;

public record AuthResponseDto(
        Long id,
        String username,
        String fullname,
        String role,
        Boolean active
) {
}
