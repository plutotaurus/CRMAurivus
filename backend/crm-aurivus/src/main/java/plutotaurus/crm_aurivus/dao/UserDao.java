package plutotaurus.crm_aurivus.dao;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import plutotaurus.crm_aurivus.domain.User;
import plutotaurus.crm_aurivus.dao.rowmapper.UserRowmapper;
import plutotaurus.crm_aurivus.exceptions.AuthenticationException;

@Repository
public class UserDao {

  private final JdbcTemplate jdbcTemplate;

  @Autowired
  public UserDao(final JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }


  public User findUserbyUsername(String username) {
    try {
      return jdbcTemplate.queryForObject("SELECT * FROM Users WHERE username = ?", new UserRowmapper(), username);
    } catch (EmptyResultDataAccessException exception) {
      throw new AuthenticationException("Invalid username or password");
    }
  }
}