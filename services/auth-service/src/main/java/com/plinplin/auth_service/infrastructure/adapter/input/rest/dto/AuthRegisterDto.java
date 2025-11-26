package com.plinplin.auth_service.infrastructure.adapter.input.rest.dto;

public record AuthRegisterDto(
        String username,
        String password,
        String fullname
) {
}
