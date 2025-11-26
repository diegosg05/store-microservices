package com.plinplin.auth_service.application.input;

import com.plinplin.auth_service.domain.entity.User;

public interface JwtUtilPort {

    String generateAccessToken(User user);

    String generateRefreshToken(User user);

    String extractUsername(String token);

    boolean isTokenValid(String token, User user);

    boolean isRefreshTokenExpired(String token);

}
