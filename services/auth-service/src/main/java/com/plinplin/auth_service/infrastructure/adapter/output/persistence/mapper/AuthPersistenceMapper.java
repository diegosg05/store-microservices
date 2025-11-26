package com.plinplin.auth_service.infrastructure.adapter.output.persistence.mapper;

import com.plinplin.auth_service.domain.entity.User;
import com.plinplin.auth_service.infrastructure.adapter.output.persistence.entity.UserEntity;

public interface AuthPersistenceMapper {
    User toDomain(UserEntity userEntity);

    UserEntity toEntity(User user);
}
