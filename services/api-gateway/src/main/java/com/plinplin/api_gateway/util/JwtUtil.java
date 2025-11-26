package com.plinplin.api_gateway.util;

public interface JwtUtil {
    <T> T extractClaim(String token, String claimName, Class<T> requiredType);

    String extractUsername(String token);

    boolean isTokenValid(String token);
}
