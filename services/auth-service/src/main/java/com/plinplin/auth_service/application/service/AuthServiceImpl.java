package com.plinplin.auth_service.application.service;

import com.plinplin.auth_service.application.input.AuthServicePort;
import com.plinplin.auth_service.application.output.AuthRepositoryPort;
import com.plinplin.auth_service.domain.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthServicePort {

    private final AuthRepositoryPort repository;

    @Override
    public User login(String username, String password) {
        return repository.login(username, password);
    }

    @Override
    public User register(User user) {
        return repository.register(user);
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return repository.findByUsername(username);
    }
}
