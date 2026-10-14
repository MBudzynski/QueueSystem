package com.example.queuesystemfacility.infrastructure.config.security;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtTokenUtil {


    private static final String SECRET = "Secretdfsdfsdfsdfsdfsdfasdfsasdfsdf";
    private static final Key SIGNING_KEY = Keys.hmacShaKeyFor(SECRET.getBytes());
    private final long jwtExpirationMs = 3600000;

    public String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + jwtExpirationMs))
                .signWith(SIGNING_KEY, SignatureAlgorithm.HS256)
                .compact();
    }

    public String getUsernameFromToken(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(SIGNING_KEY )
                .parseClaimsJws(token)
                .getBody();
        return claims.getSubject();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(SIGNING_KEY ).parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}
