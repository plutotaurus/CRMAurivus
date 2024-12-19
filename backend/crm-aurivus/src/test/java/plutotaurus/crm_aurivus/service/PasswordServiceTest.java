package plutotaurus.crm_aurivus.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@ExtendWith(MockitoExtension.class)
class PasswordServiceTest {

  private final String plainPassword = "testPassword";
  private final String hashedPassword =
      "$2a$10$Dow1yR1d4ZxzmPdz04C4CeMddJ9ox/PCgxs/Fz4DkmV0C1tT0uUu6";
  @Mock private BCryptPasswordEncoder bCryptPasswordEncoder;
  @InjectMocks private PasswordService sut;

  @DisplayName("Password is hashed")
  @Test
  void testHashPassword() {
    // Arrange
    when(bCryptPasswordEncoder.encode(plainPassword)).thenReturn(hashedPassword);

    // Act
    String result = sut.encryptPassword(plainPassword);

    // Assert
    assertAll(() -> assertEquals(hashedPassword, result));
  }

  @DisplayName("Valid password validation")
  @Test
  void testValidatePasswordSuccess() {
    // Arrange
    when(bCryptPasswordEncoder.matches(plainPassword, hashedPassword)).thenReturn(true);

    // Act
    boolean result = sut.validatePassword(plainPassword, hashedPassword);

    // Assert
    assertTrue(result, "Password should be validated successfully");
  }

  @DisplayName("Invalid password validation")
  @Test
  void testValidatePasswordFailure() {
    // Arrange
    when(bCryptPasswordEncoder.matches(plainPassword, hashedPassword)).thenReturn(false);

    // Act
    boolean result = sut.validatePassword(plainPassword, hashedPassword);
    // Assert
    assertAll(() -> assertFalse(result), () -> BCrypt.checkpw(plainPassword, hashedPassword));
  }
}
