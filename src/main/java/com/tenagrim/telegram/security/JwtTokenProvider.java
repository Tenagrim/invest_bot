package com.tenagrim.telegram.security;

import com.tenagrim.telegram.model.AppUser;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

import static io.jsonwebtoken.security.Keys.secretKeyFor;

@Component
public class JwtTokenProvider {
    @Value("${security.jwt.token.secret-key:secretkjjdsufduuewruyu588vhkcxjhdjuuewwekrj}")
    private String secretKey = "secretkjjdsufduuewruyu588vhkcxjhdjuuewwekrj";
    @Value("${security.jwt.token.expire-length:3600000}")
    private long validityInMilliseconds = 86400000; // 24h

    private Key getSigningKey() {
        return Keys.hmacShaKeyFor(secretKey.getBytes());
    }

    private static final Logger LOGGER = LoggerFactory.getLogger(JwtTokenProvider.class);

    public String generate(AppUser user) {
        return Jwts.builder()
                .setSubject(user.getUsername())
                .setIssuer("Tenagrim")
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + validityInMilliseconds))
                .signWith(getSigningKey()) // TODO remove deprecated
                .compact();

    }

    public boolean validate(String token) {
        try {
            Jwts.parser().setSigningKey(getSigningKey()).parseClaimsJws(token);
            return true;
        } catch (ExpiredJwtException ex) {
            LOGGER.error("JWT expired", ex.getMessage());
        } catch (IllegalArgumentException ex) {
            LOGGER.error("Token is null, empty or only whitespace", ex.getMessage());
        } catch (MalformedJwtException ex) {
            LOGGER.error("JWT is invalid", ex);
        } catch (UnsupportedJwtException ex) {
            LOGGER.error("JWT is not supported", ex);
        } catch (SignatureException ex) {
            LOGGER.error("Signature validation failed");
        }

        return false;
    }

    public String getSubject(String token) {
        return parseClaims(token).getSubject();
    }

    private Claims parseClaims(String token) {
        return Jwts.parser()
                .setSigningKey(getSigningKey()) // TODO remove deprecated
                .parseClaimsJws(token)
                .getBody();
    }
}
