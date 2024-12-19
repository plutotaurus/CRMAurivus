package plutotaurus.crm_aurivus.controller;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import plutotaurus.crm_aurivus.domain.JWT;
import plutotaurus.crm_aurivus.domain.UserLogin;
import plutotaurus.crm_aurivus.service.UserService;

@ExtendWith(MockitoExtension.class)
class AuthenticatorControllerTest {

  private final String token = "mock-token";
  private final JWT jwt = new JWT(token);
  private final UserLogin userLogin = new UserLogin("username", "password"); // The login request
  @Mock private UserService userService;
  @InjectMocks private AuthenticatorController sut;

  @DisplayName("Login endpoint is called and JWT token is given")
  @Test
  void testLoginEndpoint() {
    // Arrange
    when(userService.login(userLogin)).thenReturn(token);

    // Act
    ResponseEntity<JWT> result = sut.login(userLogin);

    // Assert
    assertAll(
        () -> verify(userService).login(userLogin),
        () -> assertEquals(HttpStatus.OK, result.getStatusCode()),
        () -> assertEquals(jwt.getToken(), result.getBody().getToken()));
  }
}
