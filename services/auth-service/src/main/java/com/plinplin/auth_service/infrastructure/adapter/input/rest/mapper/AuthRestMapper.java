package com.plinplin.auth_service.infrastructure.adapter.input.rest.mapper;

import com.plinplin.auth_service.domain.entity.User;
import com.plinplin.auth_service.infrastructure.adapter.input.rest.dto.AuthRegisterDto;
import com.plinplin.auth_service.infrastructure.adapter.input.rest.dto.AuthResponseDto;

public interface AuthRestMapper {
    User toDomain(AuthRegisterDto authRegisterDto);

    AuthResponseDto toDto(User user);
}
