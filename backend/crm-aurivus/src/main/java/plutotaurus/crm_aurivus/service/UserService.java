package plutotaurus.crm_aurivus.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import plutotaurus.crm_aurivus.dao.UserDao;
import plutotaurus.crm_aurivus.domain.User;
import plutotaurus.crm_aurivus.domain.UserLogin;
import plutotaurus.crm_aurivus.exceptions.AuthenticationException;

@Service
@AllArgsConstructor
public class UserService {

  private final UserDao userDao;
  private final PasswordService passwordService;
  private final JwtService jwtService;

  public String login(UserLogin userLogin) {
    User user = userDao.findUserbyUsername(userLogin.getUsername());
    if (passwordService.validatePassword(userLogin.getPassword(), user.getPasswordhash())) {
      return jwtService.generateToken(user.getUsername());
    } else {
      throw new AuthenticationException("Username or password incorrect");
    }
  }

  public void create(User user) {
    userDao.create(user);
  }
}
