package plutotaurus.crm_aurivus.service;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import plutotaurus.crm_aurivus.dao.UserDao;
import plutotaurus.crm_aurivus.domain.JWT;
import plutotaurus.crm_aurivus.domain.User;
import plutotaurus.crm_aurivus.exceptions.AuthenticationException;
import plutotaurus.crm_aurivus.security.JWTmaker;

@Service
@AllArgsConstructor
public class LoginService {

    private final UserDao userDao;
    private final JWTmaker jwtMaker;

    public String login(String username){
        User user = userDao.findUserbyUsername(username);
        System.out.print(user.getUsername());
        return jwtMaker.generateToken(user.getUsername());
    }
}
