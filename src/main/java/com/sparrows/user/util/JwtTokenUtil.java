package com.sparrows.user.util;

import com.sparrows.user.user.exception.handling.RefreshTokenExpiredException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.annotation.PostConstruct;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.time.Duration;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Base64;
import java.util.Date;

@Getter
@Component
public class JwtTokenUtil {

    @Value("${token.public-key}")
    String publicKeyPem;

    @Value("${token.private-key}")
    String privateKeyPem;

    PublicKey publicKey;

    PrivateKey privateKey;

    @Autowired
    RedisTemplate<String,String> redisTemplate;

    @PostConstruct
    public void loadPrivateKey() throws NoSuchAlgorithmException, InvalidKeySpecException {
        if (privateKeyPem == null || privateKeyPem.isBlank()) throw new IllegalStateException("No key");

        String pem = privateKeyPem
                .replace("-----BEGIN PRIVATE KEY-----", "")
                .replace("-----END PRIVATE KEY-----", "")
                .replaceAll("\\s", "");

        System.out.printf(pem);
        byte[] decoded = Base64.getDecoder().decode(pem);
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(decoded);
        privateKey = KeyFactory.getInstance("RSA").generatePrivate(keySpec);
    }

    @PostConstruct
    public void loadPublicKey() throws NoSuchAlgorithmException, InvalidKeySpecException {
        String pem = publicKeyPem
                .replace("-----BEGIN PUBLIC KEY-----", "")
                .replace("-----END PUBLIC KEY-----", "")
                .replaceAll("\\s", "");

        byte[] decoded = Base64.getDecoder().decode(pem);
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(decoded);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        publicKey = keyFactory.generatePublic(keySpec);
    }

    public String generateAccessToken(Long userId) {
        Instant now = Instant.now();
        return Jwts.builder()
                .claim("sub", userId.toString())
                .claim("type", "access")
                .setIssuedAt(Date.from(now))
                .setExpiration(Date.from(now.plus(7, ChronoUnit.DAYS)))
                .signWith(privateKey, SignatureAlgorithm.RS256)
                .compact();
    }

    public String generateRefreshToken(Long userId) {
        Instant now = Instant.now();
        String refreshToken = Jwts.builder()
                .claim("sub", userId.toString())
                .claim("type", "refresh")
                .setIssuedAt(Date.from(now))
                .setExpiration(Date.from(now.plus(7, ChronoUnit.DAYS)))
                .signWith(privateKey, SignatureAlgorithm.RS256)
                .compact();

        String redisKey = getRefreshTokenKey(userId);
        redisTemplate.opsForValue().set(redisKey, refreshToken, Duration.ofDays(7));

        return refreshToken;
    }

    public Long verifyRefreshToken(String refreshToken) {
        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(publicKey)
                    .build()
                    .parseClaimsJws(refreshToken)
                    .getBody();

            // ✅ type이 refresh인지 검증
            String type = claims.get("type", String.class);
            if (!"refresh".equals(type)) {
                throw new RuntimeException("Not a refresh token");
            }

            Long userId = Long.parseLong(claims.get("sub", String.class));
            String storedToken = redisTemplate.opsForValue().get(getRefreshTokenKey(userId));
            if (!refreshToken.equals(storedToken)) {
                throw new RuntimeException("Refresh token does not match");
            }

            return userId;
        } catch (ExpiredJwtException e) {
            throw new RefreshTokenExpiredException();
        } catch (Exception e) {
            throw new RuntimeException("Invalid refresh token");
        }
    }

    public String getRefreshTokenKey(Long userId) {
        return "REFRESH" + ":" + userId;
    }
}
