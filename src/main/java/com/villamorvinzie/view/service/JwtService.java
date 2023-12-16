package com.villamorvinzie.view.service;

import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {
    private final static SecretKey SECRET_KEY = Keys.hmacShaKeyFor(Decoders.BASE64.decode("2bcb43cbc8f6b7ef66331532881143fcbae60a879db3a8fb853f645bb24c2b3c"));

    public String getSubject(String jwt) {
        return getPayload(jwt).getSubject();
    }

    public String generateToken(UserDetails userDetails) {
        return Jwts.builder()
                .subject(userDetails.getUsername())
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + (86400000)))
                .signWith(SECRET_KEY)
                .compact();
    }

    public boolean isJwtValid(String jwt, UserDetails userDetails) {
        boolean isExpired = new Date().after(getExpirationDate(jwt));
        boolean isSubMatchUsername = getSubject(jwt).equalsIgnoreCase(userDetails.getUsername());
        return !isExpired && isSubMatchUsername;
    }

    private Date getExpirationDate(String jwt) {
        return getPayload(jwt).getExpiration();
    }

    private Claims getPayload(String jwt) {
        return Jwts.parser().verifyWith(SECRET_KEY).build().parseSignedClaims(jwt).getPayload();
    }
}
