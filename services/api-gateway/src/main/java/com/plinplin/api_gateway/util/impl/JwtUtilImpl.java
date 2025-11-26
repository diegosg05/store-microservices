package com.plinplin.api_gateway.util.impl;

import com.plinplin.api_gateway.util.JwtUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import org.springframework.beans.factory.annotation.Value;

import javax.crypto.SecretKey;
import java.util.function.Function;

public class JwtUtilImpl implements JwtUtil {

    @Value("${jwt.secret-key}")
    private String SECRET_KEY;

    @Override
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    @Override
    public <T> T extractClaim(String token, String claimName, Class<T> requiredType) {
        final Claims claims = extractAllClaims(token);
        return claims.get(claimName, requiredType);
    }

    private <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        // Usa try-catch para manejar tokens expirados o con firma inválida
        return Jwts
                .parser()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    @Override
    public boolean isTokenValid(String token) {
        try {
            extractAllClaims(token);
            return true;
        } catch (ExpiredJwtException e) {
            System.out.println("JWT Token Expirado: " + e.getMessage());
            return false;
        } catch (SignatureException e) {
            System.out.println("JWT Firma Inválida: " + e.getMessage());
            return false;
        } catch (Exception e) {
            System.out.println("JWT inválido: " + e.getMessage());
            return false;
        }
    }

    private SecretKey getSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
