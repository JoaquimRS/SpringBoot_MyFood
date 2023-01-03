package com.myfood.springboot_myfood.security.jwt;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.time.Instant;
import java.util.Date;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

public class JwtUtils {
    private final Long validSeconds;
    private final Key key;

    public JwtUtils(String signKey, Long validSeconds) {
        this.validSeconds = validSeconds;
        key = Keys.hmacShaKeyFor(signKey.getBytes(StandardCharsets.UTF_8));
    }

    public String encode(String sub) {
        if (sub == null || sub.equals("")) {
            return null;
        }

        Instant exp = Instant.now();
        return Jwts.builder()
                .setSubject(sub)
                .setIssuedAt(new Date(exp.toEpochMilli()))
                .setExpiration(
                        new Date(exp.toEpochMilli()
                                + validSeconds
                                * 1000))
                .signWith(SignatureAlgorithm.valueOf("HS256"), key)
                .compact();
    }

    public boolean validateToken(String jwt) {
        try {
            Claims claims = Jwts.parser().setSigningKey(key).parseClaimsJws(jwt).getBody();
            Instant now = Instant.now();
            Date exp = claims.getExpiration();

            return exp.after(Date.from(now));
        } catch (JwtException e) {
            return false;
        }
    }

    public String getSub(String jwt) {
        try {
            Claims claims = Jwts.parser().setSigningKey(key).parseClaimsJws(jwt).getBody();
            return claims.getSubject();
        } catch (JwtException e) {
            return null;
        }
    }
}
