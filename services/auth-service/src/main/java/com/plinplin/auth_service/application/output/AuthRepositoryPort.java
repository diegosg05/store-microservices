package com.plinplin.auth_service.application.output;

import com.plinplin.auth_service.domain.entity.User;

import java.util.Optional;

public interface AuthRepositoryPort {
    User login(String username, String password);

    User register(User user);

    Optional<User> findByUsername(String username);
}
