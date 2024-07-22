package com.notification.service.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import java.nio.charset.StandardCharsets;
import java.util.Base64; 
import java.util.Date;
import java.util.Optional;
import jakarta.annotation.PostConstruct;
import javax.crypto.SecretKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@SuppressWarnings("deprecation")
@Component
public class TokenProvider {
    private static final Logger log = LoggerFactory.getLogger(TokenProvider.class);
    @SuppressWarnings("unused")
    private static final String AUTHORITIES_KEY = "roles";
    private SecretKey secretKey;
    private String secretKeyString = "rzxlszyykpbgqcflzxsqcysyhljt";
    private long validityInMs = 36000000000000L;

    @PostConstruct
    protected void init() {
        String secret = Base64.getEncoder().encodeToString(this.secretKeyString.getBytes());
        this.secretKey = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
    }

    @SuppressWarnings("deprecation")
    public String createToken(String username, String authorities) {
        Claims claims = Jwts.claims().setSubject(username);
        if (!authorities.isEmpty()) {
            claims.put("roles", authorities);
        }

        Date now = new Date();
        Date validity = new Date(now.getTime() + this.validityInMs);
        return Jwts.builder().setClaims(claims).setIssuedAt(now).setExpiration(validity).signWith(this.secretKey, SignatureAlgorithm.HS256).compact();
    }

    @SuppressWarnings("deprecation")
    public boolean validateToken(String token) {
        try {
            Jws<Claims> claims = Jwts.parserBuilder().setSigningKey(this.secretKey).build().parseClaimsJws(token);
            log.info("expiration date: {}", ((Claims)claims.getBody()).getExpiration());
            return true;
        } catch (IllegalArgumentException | JwtException var3) {
            log.info("Invalid JWT token: {}", var3.getMessage());
            log.trace("Invalid JWT token trace.", var3);
            return false;
        }
    }

    @SuppressWarnings("deprecation")
    public Optional<Jws<Claims>> getClaims(Optional<String> token) {
        if (!token.isPresent()) {
            return Optional.empty();
        } else {
            return Optional.of(Jwts.parser().setSigningKey(this.secretKey).parseClaimsJws((String)token.get()));
        }
    }

    @SuppressWarnings("deprecation")
    public String getUsername(String token) {
        return ((Claims)Jwts.parser().setSigningKey(this.secretKey).parseClaimsJws(token).getBody()).getSubject();
    }

    public Claims getAllClaimsFromToken(String token) {
        return (Claims)Jwts.parserBuilder().setSigningKey(this.secretKey).build().parseClaimsJws(token).getBody();
    }

    public String getUsernameFromToken(String token) {
        return this.getAllClaimsFromToken(token).getSubject();
    }

    public TokenProvider() {
    }
}
