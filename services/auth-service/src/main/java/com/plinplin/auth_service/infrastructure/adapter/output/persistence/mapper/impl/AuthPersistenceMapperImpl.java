package com.plinplin.auth_service.infrastructure.adapter.output.persistence.mapper.impl;

import com.plinplin.auth_service.domain.entity.User;
import com.plinplin.auth_service.infrastructure.adapter.output.persistence.entity.UserEntity;
import com.plinplin.auth_service.infrastructure.adapter.output.persistence.mapper.AuthPersistenceMapper;
import org.springframework.stereotype.Component;

@Component
public class AuthPersistenceMapperImpl implements AuthPersistenceMapper {
    @Override
    public User toDomain(UserEntity userEntity) {
        return User.builder()
                .id(userEntity.getId())
                .active(userEntity.getActive())
                .fullname(userEntity.getFullname())
                .role(userEntity.getRoleEnum())
                .password(userEntity.getPassword())
                .username(userEntity.getUsername())
                .build();
    }

    @Override
    public UserEntity toEntity(User user) {
        return UserEntity.builder()
                .active(user.getActive())
                .username(user.getUsername())
                .id(user.getId())
                .roleEnum(user.getRole())
                .fullname(user.getFullname())
                .password(user.getPassword())
                .build();
    }
}
