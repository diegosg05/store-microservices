package com.plinplin.auth_service.infrastructure.adapter.input.rest.mapper.impl;

import com.plinplin.auth_service.domain.entity.User;
import com.plinplin.auth_service.domain.enums.RoleEnum;
import com.plinplin.auth_service.infrastructure.adapter.input.rest.dto.AuthRegisterDto;
import com.plinplin.auth_service.infrastructure.adapter.input.rest.dto.AuthResponseDto;
import com.plinplin.auth_service.infrastructure.adapter.input.rest.mapper.AuthRestMapper;
import org.springframework.stereotype.Component;

@Component
public class AuthRestMapperImpl implements AuthRestMapper {
    @Override
    public User toDomain(AuthRegisterDto authRegisterDto) {
        return User.builder()
                .active(true)
                .role(RoleEnum.ADMIN)
                .fullname(authRegisterDto.fullname())
                .username(authRegisterDto.username())
                .password(authRegisterDto.password())
                .build();
    }

    @Override
    public AuthResponseDto toDto(User user) {
        return new AuthResponseDto(
                user.getId(),
                user.getUsername(),
                user.getFullname(),
                user.getRole().name(),
                user.getActive()
        );
    }
}
