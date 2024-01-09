package com.amadeus.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.function.Function;

@Service

public class JwtService {

    @Value("${jwt.key}")
    private String SECRET_KEY;
    public String findUsername(String jwt) {
        return exportToken(jwt, Claims::getSubject);
    }

    private <T> T exportToken(String jwt, Function<Claims,T> claimsTFunction) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(getSignKey())
                .build()
                .parseClaimsJws(jwt)
                .getBody();
        return claimsTFunction.apply(claims);
    }

    private Key getSignKey() {
        byte[] key = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(key);
    }

    public boolean tokenControl(String jwt, UserDetails userDetails) {
        final String username = findUsername(jwt);
        return (username.equals(userDetails.getUsername()) &&
                !exportToken(jwt, Claims::getExpiration).before(new Date(System.currentTimeMillis())));
    }

    public String generateToken(UserDetails user) {
        return Jwts.builder()
                .setClaims(new HashMap<>())
                .setSubject(user.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 24))
                .signWith(getSignKey(), SignatureAlgorithm.HS256)
                .compact();
    }
}
