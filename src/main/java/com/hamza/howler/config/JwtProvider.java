package com.hamza.howler.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.Authentication;

import javax.crypto.SecretKey;
import java.util.Date;

public class JwtProvider {
    SecretKey key= Keys.hmacShaKeyFor(JwtConstant.SECRET_KEY.getBytes());

    public String generateJwt(Authentication authentication){
        return Jwts
                .builder()
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis()+86400000))
                .claim("email",authentication.getName())
                .signWith(key)
                .compact();
    }

    public String getEmail(String jwt){
        jwt=jwt.substring(7);
        Claims claims= Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJwt(jwt).getBody();
        String email=String.valueOf(claims.get("email"));
        return email;
    }
}
