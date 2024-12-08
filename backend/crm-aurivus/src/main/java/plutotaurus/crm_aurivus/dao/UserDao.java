package plutotaurus.crm_aurivus.dao;

import lombok.AllArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import plutotaurus.crm_aurivus.domain.User;
import plutotaurus.crm_aurivus.dao.rowmapper.UserRowmapper;
import plutotaurus.crm_aurivus.exceptions.AuthenticationException;

@AllArgsConstructor
@Repository
public class UserDao {

  private final JdbcTemplate jdbcTemplate;

  public User findUserbyUsername(String username) {
    try {
      return jdbcTemplate.queryForObject("SELECT * FROM Users WHERE username = ?", new UserRowmapper(), username);
    } catch (EmptyResultDataAccessException exception) {
      throw new AuthenticationException("Invalid username or password");
    }
  }

  public void create(User user) {
    jdbcTemplate.update("INSERT INTO Users (username, passwordhash) VALUES (?, ?)",
            user.getUsername(), user.getPasswordhash());
  }

}
