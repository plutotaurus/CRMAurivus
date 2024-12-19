package plutotaurus.crm_aurivus.dao.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import plutotaurus.crm_aurivus.domain.User;

public class UserRowMapper implements RowMapper<User> {
  @Override
  public User mapRow(ResultSet rs, int rowNum) throws SQLException {
    return new User(rs.getInt("id"), rs.getString("username"), rs.getString("passwordhash"));
  }
}
