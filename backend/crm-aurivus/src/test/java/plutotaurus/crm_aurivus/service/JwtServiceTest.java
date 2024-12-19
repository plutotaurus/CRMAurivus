package plutotaurus.crm_aurivus.service;

import static java.time.temporal.ChronoUnit.DAYS;
import static org.junit.jupiter.api.Assertions.*;
import static plutotaurus.crm_aurivus.configuration.JwtConfiguration.SECRET_KEY;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import java.time.Instant;
import java.util.Date;
import javax.crypto.SecretKey;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import plutotaurus.crm_aurivus.exceptions.AuthenticationException;

@ExtendWith(MockitoExtension.class)
class JwtServiceTest {

  private final JwtService sut = new JwtService();
  private final String username = "username";


    @DisplayName("JWT token is generated")
    @Test
    void testGenerateToken() {
        // Arrange
        int tokenValidityDays = 7;
        Instant now = Instant.now();
        Instant expectedExpiration = now.plus(tokenValidityDays, DAYS);

        // Act
        String result = sut.generateToken(username);

        // Assert
        Claims claims = Jwts.parser()
                .verifyWith((SecretKey) SECRET_KEY)
                .build()
                .parseSignedClaims(result)
                .getPayload();

        assertAll(
                () -> assertEquals(username, claims.getSubject()),
                () -> assertTrue(claims.getIssuedAt().toInstant().isBefore(now.plusSeconds(1))),
                () -> assertEquals(expectedExpiration.truncatedTo(DAYS), claims.getExpiration().toInstant().truncatedTo(DAYS))
        );
    }

    @DisplayName("Token is valid")
    @Test
    void testValidateTokenSuccessful() {
      //Arrange
        String token = sut.generateToken(username);
        String authHeader = "Bearer " + token;

        // Act
        Claims claims = sut.validateToken(authHeader);

        // Assert
        assertAll(
                () -> assertEquals(username, claims.getSubject()),
                () -> assertNotNull(claims.getExpiration()),
                () -> assertTrue(claims.getExpiration().after(new Date()))
        );
    }
    @DisplayName("Token is invalid")
    @Test
    void testValidateTokenInvalid() {
        // Arrange
        String token = "invalidToken";
        String authHeader = "Bearer " + token;

        // Act & Assert
        AuthenticationException exception = assertThrows(
                AuthenticationException.class,
                () -> sut.validateToken(authHeader)
        );

        assertAll(
                () -> assertEquals("Invalid or expired token", exception.getMessage())
        );
    }
    @DisplayName("Token is expired")
    @Test
    void testValidateTokenExpired() {
        // Arrange
        String expiredToken = sut.generateToken(username);

        // Simulate expiration by setting a past expiration date
        Instant pastExpiration = Instant.now().minus(1, DAYS);
        expiredToken = Jwts.builder()
                .subject(username)
                .expiration(Date.from(pastExpiration))
                .signWith(SECRET_KEY)
                .compact();

        String authHeader = "Bearer " + expiredToken;

        // Act & Assert
        AuthenticationException exception = assertThrows(
                AuthenticationException.class,
                () -> sut.validateToken(authHeader)
        );

        assertAll(
                () -> assertEquals("Invalid or expired token", exception.getMessage())
        );

    }
}
