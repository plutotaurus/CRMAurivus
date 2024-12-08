package plutotaurus.crm_aurivus.configuration;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.ApplicationArguments;
import plutotaurus.crm_aurivus.domain.User;
import plutotaurus.crm_aurivus.service.PasswordService;
import plutotaurus.crm_aurivus.service.UserService;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserConfigurationTest {

     @Mock private PasswordService passwordService;
     @Mock private UserService userService;
     @Mock private ApplicationArguments applicationArguments;
     @InjectMocks private UserConfiguration sut;

     @DisplayName("User is created during runtime")
     @Test
     void testRunCreatesUser() {
         // Arrange
         String encryptedPassword = "encryptedPassword123";
         when(passwordService.encryptPassword("user1!")).thenReturn(encryptedPassword);

         // Act
         sut.run(applicationArguments);
         ArgumentCaptor<User> userCaptor = ArgumentCaptor.forClass(User.class);
         verify(userService).create(userCaptor.capture());
         User createdUser = userCaptor.getValue();

         // Assert
         assertAll(
                 () -> assertEquals(1, createdUser.getId()),
                 () -> assertEquals("usereen", createdUser.getUsername()),
                 () -> assertEquals(encryptedPassword, createdUser.getPasswordhash()),
                 () -> verify(passwordService).encryptPassword("user1!"),
                 () -> verify(userService).create(any(User.class))

         );
     }
}
