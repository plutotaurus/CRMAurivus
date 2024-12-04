package plutotaurus.crm_aurivus.security;

import static java.time.temporal.ChronoUnit.DAYS;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.time.Instant;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import plutotaurus.crm_aurivus.exceptions.AuthenticationException;

/** Utility class for handling JSON web token operations. */
@Component
public class JWTmaker {

    private final SecretKey secretKey;
    private static final String ALGORITHM = "PBKDF2WithHmacSHA1";
    private static final int ITERATIONS = 65536;
    private static final int KEY_LENGTH = 64 * 8;

    // Use @Value to externalize the secret key
    public JWTmaker(@Value("${jwt.secret}") String secret) {
        this.secretKey = Keys.hmacShaKeyFor(Decoders.BASE64.decode(secret));
    }

    public String generateToken(String username) {

        Instant issuedAt = Instant.now();
        int tokenValidityDays = 7;
        Instant expiresAt = issuedAt.plus(tokenValidityDays, DAYS);

        return Jwts.builder()
                .subject(username)
                .issuedAt(Date.from(issuedAt))
                .expiration(Date.from(expiresAt))
                .signWith(secretKey)
                .compact();
    }

    public void verifyToken(String token) {
        try {
            Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token);
        } catch (Exception e) {
            throw new AuthenticationException("Authentication failed");
        }
    }


    public String verifyAndExtractUsernameFromToken(String token) {
        try {
            return Jwts.parser()
                    .verifyWith(secretKey)
                    .build()
                    .parseSignedClaims(token)
                    .getPayload()
                    .getSubject();
        } catch (Exception e) {
      throw new AuthenticationException("Authentication failed");
        }
    }


    public static HashMap<String, String> hashPassword(String password)
            throws NoSuchAlgorithmException, InvalidKeySpecException {
        HashMap<String, String> passwordHashAndSalt = new HashMap<>();

        // Generate a random salt
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        passwordHashAndSalt.put("salt", Base64.getEncoder().encodeToString(salt));

        // Hash the password using PBKDF2
        PBEKeySpec spec = new PBEKeySpec(password.toCharArray(), salt, ITERATIONS, KEY_LENGTH);
        SecretKeyFactory factory = SecretKeyFactory.getInstance(ALGORITHM);
        byte[] hash = factory.generateSecret(spec).getEncoded();
        passwordHashAndSalt.put("hash", Base64.getEncoder().encodeToString(hash));

        return passwordHashAndSalt;
    }
}
