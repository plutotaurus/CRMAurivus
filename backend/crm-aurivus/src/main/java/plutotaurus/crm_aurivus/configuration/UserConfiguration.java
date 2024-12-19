package plutotaurus.crm_aurivus.configuration;

import lombok.AllArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Configuration;
import plutotaurus.crm_aurivus.domain.User;
import plutotaurus.crm_aurivus.service.PasswordService;
import plutotaurus.crm_aurivus.service.UserService;

@AllArgsConstructor
@Configuration
public class UserConfiguration implements ApplicationRunner {

  private final PasswordService passwordService;
  private final UserService userService;

  @Override
  public void run(ApplicationArguments args) {
    User user1 = new User(1, "usereen", passwordService.encryptPassword("user1!"));
    userService.create(user1);
  }
}
