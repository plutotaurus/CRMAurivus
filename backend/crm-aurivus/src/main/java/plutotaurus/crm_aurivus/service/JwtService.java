package plutotaurus.crm_aurivus.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.stereotype.Service;
import plutotaurus.crm_aurivus.configuration.JwtConfiguration;
import plutotaurus.crm_aurivus.exceptions.AuthenticationException;


import javax.crypto.SecretKey;

import java.security.Key;
import java.time.Instant;
import java.util.Date;

import static java.time.temporal.ChronoUnit.DAYS;

@Service
public class JwtService {

  private static final Key SECRET_KEY = JwtConfiguration.SECRET_KEY;

  public String generateToken(String username) {
    int tokenValidityDays = 7;
    Instant expiresAt = Instant.now().plus(tokenValidityDays, DAYS);

    return Jwts.builder()
        .subject(username)
        .issuedAt(new Date())
        .expiration(Date.from(expiresAt))
        .signWith(SECRET_KEY)
        .compact();
  }

  public Claims validateToken(String authHeader) {
    String token = authHeader != null ? authHeader.replace("Bearer ", "") : null;
      try {
      return Jwts.parser()
              .verifyWith((SecretKey) SECRET_KEY)
              .build()
              .parseSignedClaims(token)
              .getPayload();
    } catch (Exception exception) {
      throw new AuthenticationException("Invalid or expired token");
    }
  }
}

