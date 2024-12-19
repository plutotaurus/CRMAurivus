package plutotaurus.crm_aurivus.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserLogin {
  private String username;
  private String password;
}
