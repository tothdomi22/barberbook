package com.dominik.barberbook.services;

import com.dominik.barberbook.config.JwtConfig;
import com.dominik.barberbook.entities.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import java.util.Date;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class JwtService {
  private final JwtConfig jwtConfig;

  public Jwt generateAccessToken(User user) {
    return generateToken(user, jwtConfig.getAccessTokenExpiration());
  }

  public Jwt generateRefreshToken(User user) {
    return generateToken(user, jwtConfig.getRefreshTokenExpiraion());
  }

  private Jwt generateToken(User user, long tokenExpriation) {
    var claims =
        Jwts.claims()
            .subject(user.getId().toString())
            .add("email", user.getEmail())
            .add("role", user.getRole())
            .issuedAt(new Date())
            .expiration(new Date(System.currentTimeMillis() + 1000 * tokenExpriation))
            .build();
    return new Jwt(claims, jwtConfig.getSecretKey());
  }

  public Jwt parseToken(String token) {
    try {
      var claims = getClaims(token);
      return new Jwt(claims, jwtConfig.getSecretKey());
    } catch (JwtException e) {
      return null;
    }
  }

  private Claims getClaims(String token) {
    return Jwts.parser()
        .verifyWith(jwtConfig.getSecretKey())
        .build()
        .parseSignedClaims(token)
        .getPayload();
  }
}
