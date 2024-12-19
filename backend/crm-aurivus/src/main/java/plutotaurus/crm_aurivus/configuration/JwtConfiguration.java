package plutotaurus.crm_aurivus.configuration;

import io.jsonwebtoken.security.Keys;
import java.security.Key;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JwtConfiguration {
  private static final String SECRET =
      "VGhpc0lzQVNlYeVyZTMyQnl0ZUhlYWRlcg==gfhvjbnwsafvhjshgWVjwhkjb";
  public static final Key SECRET_KEY = Keys.hmacShaKeyFor(SECRET.getBytes());
}
