package com.plinplin.auth_service.infrastructure.adapter.output.persistence;

import com.plinplin.auth_service.application.output.AuthRepositoryPort;
import com.plinplin.auth_service.domain.entity.User;
import com.plinplin.auth_service.domain.exception.InvalidCredentialsException;
import com.plinplin.auth_service.domain.exception.UserNotFoundException;
import com.plinplin.auth_service.infrastructure.adapter.output.persistence.mapper.AuthPersistenceMapper;
import com.plinplin.auth_service.infrastructure.adapter.output.persistence.repository.AuthJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class AuthPersistenceAdapter implements AuthRepositoryPort {

    private final AuthJpaRepository repository;
    private final AuthPersistenceMapper mapper;
    private final PasswordEncoder encoder;

    @Override
    public User login(String username, String password) {
        var optionalUser = repository.findByUsername(username);

        if (optionalUser.isEmpty()) {
            throw new UserNotFoundException("El usuario no se ha encontrado");
        }

        var user = optionalUser.get();

        if (!encoder.matches(password, user.getPassword())) {
            throw new InvalidCredentialsException("La contraseña ingresada es incorrecta");
        }

        return mapper.toDomain(user);
    }

    @Override
    public User register(User user) {
        var optionalUser = repository.findByUsername(user.getUsername());

        if (optionalUser.isPresent()) {
            throw new InvalidCredentialsException("El usuario ya se encuentra en uso");
        }

        var userSaved = repository.save(mapper.toEntity(user));

        return mapper.toDomain(userSaved);
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return repository.findByUsername(username)
                .map(mapper::toDomain);
    }
}
