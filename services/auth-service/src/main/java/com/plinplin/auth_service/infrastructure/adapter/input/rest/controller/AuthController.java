package com.plinplin.auth_service.infrastructure.adapter.input.rest.controller;

import com.plinplin.auth_service.application.input.AuthServicePort;
import com.plinplin.auth_service.application.input.JwtUtilPort;
import com.plinplin.auth_service.domain.exception.UserNotFoundException;
import com.plinplin.auth_service.infrastructure.adapter.input.rest.dto.AuthLoginDto;
import com.plinplin.auth_service.infrastructure.adapter.input.rest.dto.AuthRegisterDto;
import com.plinplin.auth_service.infrastructure.adapter.input.rest.dto.AuthResponseDto;
import com.plinplin.auth_service.infrastructure.adapter.input.rest.mapper.AuthRestMapper;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/auth")
@RestController
public class AuthController {

    private final AuthRestMapper mapper;

    private final AuthServicePort service;

    private final JwtUtilPort jwtUtil;

    @Value("${jwt.access-expiration-ms}")
    private long ACCESS_EXPIRATION_TIME;

    @Value("${jwt.refresh-expiration-ms}")
    private long REFRESH_EXPIRATION_TIME;

    @PostMapping("/register")
    public ResponseEntity<AuthResponseDto> register(@RequestBody AuthRegisterDto authRegisterDto, HttpServletResponse response) {

        var userSaved = service.register(mapper.toDomain(authRegisterDto));

        var accessToken = jwtUtil.generateAccessToken(userSaved);

        var refreshToken = jwtUtil.generateRefreshToken(userSaved);

        setAuthCookies(response, accessToken, refreshToken);

        return ResponseEntity.ok(mapper.toDto(userSaved));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDto> login(@RequestBody AuthLoginDto authLoginDto, HttpServletResponse response) {

        var userAuthenticated = service.login(authLoginDto.username(), authLoginDto.password());

        var accessToken = jwtUtil.generateAccessToken(userAuthenticated);

        var refreshToken = jwtUtil.generateRefreshToken(userAuthenticated);

        setAuthCookies(response, accessToken, refreshToken);

        return ResponseEntity.ok(mapper.toDto(userAuthenticated));
    }

    @PostMapping("/refresh")
    public ResponseEntity<Void> refreshToken(@CookieValue(name = "refresh_token") String refreshToken, HttpServletResponse response) {

        if (jwtUtil.isRefreshTokenExpired(refreshToken)) {
            throw new RuntimeException("Refresh token expirado o invalido");
        }

        String username = jwtUtil.extractUsername(refreshToken);

        var user = service.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException("No se ha encontrado el usuario"));

        String newAccessToken = jwtUtil.generateAccessToken(user);
        String newRefreshToken = jwtUtil.generateRefreshToken(user);

        setAuthCookies(response, newAccessToken, newRefreshToken);

        return ResponseEntity.noContent().build();
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logout(HttpServletResponse response) {
        deleteAuthCookies(response);
        return ResponseEntity.noContent().build();
    }

    private void setAuthCookies(HttpServletResponse response, String accessToken, String refreshToken) {

        ResponseCookie accessCookie = ResponseCookie.from("access_token", accessToken)
                .httpOnly(true)
                .secure(false)
                .path("/")
                .maxAge(ACCESS_EXPIRATION_TIME)
                .sameSite("Strict")
                .build();

        ResponseCookie refreshCookie = ResponseCookie.from("refresh_token", refreshToken)
                .httpOnly(true)
                .secure(false)
                .path("/auth/refresh")
                .maxAge(REFRESH_EXPIRATION_TIME)
                .sameSite("Strict")
                .build();

        response.addHeader("Set-Cookie", accessCookie.toString());
        response.addHeader("Set-Cookie", refreshCookie.toString());

    }

    private void deleteAuthCookies(HttpServletResponse response) {

        ResponseCookie accessCookie = ResponseCookie.from("access_token", "")
                .path("/")
                .maxAge(0)
                .build();

        ResponseCookie refreshCookie = ResponseCookie.from("refresh_token", "")
                .path("/auth/refresh")
                .maxAge(0)
                .build();

        response.addHeader("Set-Cookie", accessCookie.toString());
        response.addHeader("Set-Cookie", refreshCookie.toString());

    }
}
