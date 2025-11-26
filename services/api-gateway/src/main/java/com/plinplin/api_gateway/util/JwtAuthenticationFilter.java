package com.plinplin.api_gateway.util;

import lombok.RequiredArgsConstructor;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.List;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter implements GatewayFilter {

    private final JwtUtil jwtUtil;

    // Rutas que no requieren autenticacion
    public static final List<String> OPEN_ENDPOINTS = List.of(
            "/auth/login",
            "/auth/register",
            "/auth/logout",
            "/auth/refresh"
    );

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        final String path = request.getURI().getPath();

        if (OPEN_ENDPOINTS.stream().anyMatch(path::contains)) {
            return chain.filter(exchange);
        }

        var tokenCookie = request.getCookies().getFirst("access_token");

        String jwt = null;
        if (tokenCookie != null) {
            jwt = tokenCookie.getValue();
        }

        if (jwt == null || !jwtUtil.isTokenValid(jwt)) {
            // Si no hay token o es inválido/expirado, rechazar
            return this.onError(exchange, HttpStatus.UNAUTHORIZED);
        }

        try {
            String username = jwtUtil.extractUsername(jwt);
            String role = jwtUtil.extractClaim(jwt, "role", String.class);

            ServerHttpRequest modifiedRequest = request.mutate()
                    .header("X-Auth-User", username)
                    .header("X-Auth-Role", role)
                    .build();

            return chain.filter(exchange.mutate().request(modifiedRequest).build());

        } catch (Exception e) {
            // Error al parsear el token
            return this.onError(exchange, HttpStatus.FORBIDDEN);
        }
    }

    private Mono<Void> onError(ServerWebExchange exchange, HttpStatus httpStatus) {
        ServerHttpResponse response = exchange.getResponse();
        response.setStatusCode(httpStatus);
        return response.setComplete();
    }
}
