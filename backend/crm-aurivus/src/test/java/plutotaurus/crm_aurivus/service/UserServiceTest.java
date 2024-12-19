package plutotaurus.crm_aurivus.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import plutotaurus.crm_aurivus.dao.UserDao;
import plutotaurus.crm_aurivus.domain.User;
import plutotaurus.crm_aurivus.domain.UserLogin;
import plutotaurus.crm_aurivus.exceptions.AuthenticationException;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {
  private final UserLogin userLogin = new UserLogin("username", "password");
  private final User user = new User(1, "username", "password");
  private final String token = "token";
  @Mock private UserDao userDao;
  @Mock private PasswordService passwordService;
  @Mock private JwtService jwtService;
  @InjectMocks private UserService sut;

  @DisplayName("User found and password correct and gives token")
  @Test
  void testLoginSuccessful() {
    // Arrange
    when(userDao.findUserByUsername(userLogin.getUsername())).thenReturn(user);
    when(passwordService.validatePassword(userLogin.getPassword(), user.getPasswordhash()))
        .thenReturn(true);
    when(jwtService.generateToken(user.getUsername())).thenReturn(token);

    // Act
    String result = sut.login(userLogin);

    // Assert
    assertAll(
        () -> verify(userDao).findUserByUsername(userLogin.getUsername()),
        () ->
            verify(passwordService)
                .validatePassword(userLogin.getPassword(), user.getPasswordhash()),
        () -> verify(jwtService).generateToken(user.getUsername()),
        () -> assertEquals(token, result));
  }

  @DisplayName("User found but password not correct and throws exception")
  @Test
  void testLoginUnsuccessful() {
    // Arrange
    when(userDao.findUserByUsername(userLogin.getUsername())).thenReturn(user);
    when(passwordService.validatePassword(userLogin.getPassword(), user.getPasswordhash()))
        .thenReturn(false);

    // Act & Assert
    AuthenticationException exception =
        assertThrows(AuthenticationException.class, () -> sut.login(userLogin));

    assertAll(
        () -> verify(userDao).findUserByUsername(userLogin.getUsername()),
        () ->
            verify(passwordService)
                .validatePassword(userLogin.getPassword(), user.getPasswordhash()),
        () -> assertEquals("Username or password incorrect", exception.getMessage()));
  }

  @DisplayName("Function is called in database to create user")
  @Test
  void testCreate() {
    // Assert
    doNothing().when(userDao).create(user);

    // Act
    sut.create(user);

    // Assert
    assertAll(() -> verify(userDao).create(user));
  }
}
