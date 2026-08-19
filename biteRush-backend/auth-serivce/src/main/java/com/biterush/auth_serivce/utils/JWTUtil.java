package com.biterush.auth_serivce.utils;

import com.biterush.auth_serivce.model.entity.UserCredentials;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JWTUtil {

    @Value("${jwt.AccessTokenExpiration}")
    private Long ACCESS_TOKEN_EXPIRATION_TIME;
    @Value("${jwt.refreshTokenExpiration}")
    private Long REFRESH_TOKEN_EXPIRATION_TIME;
    @Value("${jwt.secret}")
    private String SECRET_KEY;

    public SecretKey getSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public String generateAccessToken(UserCredentials user) {
        Map<String,String> claims_map = new HashMap<>();
        claims_map.put("firstName",user.getFirstName());
        claims_map.put("lastName",user.getLastName());
        claims_map.put("userId", String.valueOf(user.getUserId()));
        String token =  Jwts.builder()
                .subject(user.getEmail())
                .claims(claims_map)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + ACCESS_TOKEN_EXPIRATION_TIME))
                .signWith(getSigningKey())
                .compact();
        System.out.println("TOKEN IN USER SERVICE"+token);

        Claims c = Jwts.parser()
                .verifyWith(getSigningKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();

        c.forEach((k, v) -> System.out.println(k + " = " + v));
        return token;
    }
    public String generateRefreshToken(UserCredentials user) {
        return Jwts.builder()
                .subject(user.getEmail())
                .claim("firstName",user.getFirstName())
                .claim("lastName",user.getLastName())
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + REFRESH_TOKEN_EXPIRATION_TIME))
                .signWith(getSigningKey())
                .compact();
    }

    public boolean isValid(String token, UserDetails userDetails) {
        String username = extractUsername(token);
        return username.equals(userDetails.getUsername())
                && !isTokenExpired(token);
    }

    public String extractUsername(String token) {
        return Jwts.parser()
                .verifyWith(getSigningKey())
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();
    }

    public Date extractExpiration(String token) {
        return Jwts.parser()
                .verifyWith(getSigningKey())
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getExpiration();
    }

    public boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

}
