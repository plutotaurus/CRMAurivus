package plutotaurus.crm_aurivus.service;

import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class PasswordService {

  private final BCryptPasswordEncoder bCryptPasswordEncoder;

  public String encryptPassword(String rawPassword) {
    return bCryptPasswordEncoder.encode(rawPassword);
  }

  public boolean validatePassword(String rawPassword, String encodedPassword) {
    return bCryptPasswordEncoder.matches(rawPassword, encodedPassword);
  }
}
