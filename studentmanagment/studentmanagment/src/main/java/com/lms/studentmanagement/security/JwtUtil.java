package com.lms.studentmanagement.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.Set;

@Slf4j
@Component
public class JwtUtil {
    private final String jwtSecret = "mySuperSecretKeyForJwtTokenThatIsVeryLong12345    ";
    private final long jwtExpirationMs = 3600000; // 1 hour

    private Key getSigningKey() {
        return Keys.hmacShaKeyFor(jwtSecret.getBytes());
    }

    public String generateToken(Long userId, String email, Set<String> roles) {
        return Jwts.builder()
                .setSubject(email)
                .claim("userId", userId)
                .claim("roles", roles)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + jwtExpirationMs))
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public Claims extractJwtToken(String token) {
        try {
            return getClaimsFromJwtToken(token);
        } catch (ExpiredJwtException e) {
            log.error("❌ Token expired: {}", e.getMessage());
            throw e;
        } catch (io.jsonwebtoken.security.SignatureException e) {
            log.error("❌ Invalid signature: {}", e.getMessage());
            throw e;
        } catch (Exception e) {
            log.error("❌ Invalid token: {}", e.getMessage());
            throw e;
        }
    }

    public Claims getClaimsFromJwtToken(String token) {
        return Jwts.parser().setSigningKey(getSigningKey()).build().parseClaimsJws(token).getBody();
    }
}