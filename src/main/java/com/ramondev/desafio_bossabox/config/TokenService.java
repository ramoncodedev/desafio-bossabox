package com.ramondev.desafio_bossabox.config;


import com.ramondev.desafio_bossabox.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.Date;

@Component
public class TokenService {

    private final String secretKey = "my-super-secret-key-for-jwt-authentication-2026";

    public String genereteToken(User user){

        return Jwts.builder()
                .subject(user.getEmail())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60))
                .signWith(Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8)),  SignatureAlgorithm.HS256)
                .compact();
    }

    public Claims extractClaims(String token){
        return Jwts.parser()
                .setSigningKey(Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8)))
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public String extractUserName(String token){
        return extractClaims(token.trim()).getSubject();
    }

    public boolean isTokeExpiration(String token){
        return extractClaims(token).getExpiration().before(new Date());
    }

    public boolean validateToken(String token, String userName){
       final String extractUserName = extractUserName(token);

       return (extractUserName.equals(userName) && !isTokeExpiration(token));
    }
}
