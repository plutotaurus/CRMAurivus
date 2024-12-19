package plutotaurus.crm_aurivus.configuration;

import static org.junit.jupiter.api.Assertions.*;

import lombok.AllArgsConstructor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@AllArgsConstructor
@SpringBootTest(classes = SecurityConfiguration.class)
class SecurityConfigurationTest {

  private ApplicationContext applicationContext;

  @DisplayName("PasswordEcryptor bean is created")
  @Test
  void testBCryptPasswordEncoderBean() {
    // Act
    Object bean = applicationContext.getBean("bCryptPasswordEncoder");

    // Assert
    assertAll(() -> assertNotNull(bean), () -> assertInstanceOf(BCryptPasswordEncoder.class, bean));
  }
}
