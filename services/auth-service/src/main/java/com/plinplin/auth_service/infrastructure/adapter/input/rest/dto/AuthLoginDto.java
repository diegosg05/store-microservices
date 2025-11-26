package com.plinplin.auth_service.infrastructure.adapter.input.rest.dto;

public record AuthLoginDto(
        String username,
        String password
) {
}
