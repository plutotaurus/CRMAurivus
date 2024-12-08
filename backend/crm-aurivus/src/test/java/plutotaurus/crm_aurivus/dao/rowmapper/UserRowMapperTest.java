package plutotaurus.crm_aurivus.dao.rowmapper;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import plutotaurus.crm_aurivus.domain.User;

import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
@ExtendWith(MockitoExtension.class)
class UserRowMapperTest {
  private final User user = new User(1, "password",  "iowefjmlksdc");
  @Mock private ResultSet resultSet;
  private final UserRowMapper sut = new UserRowMapper();

  @DisplayName("Rowmapper for user works")
  @Test
  void TestMapRowWorks() throws SQLException {
    //Arrange
    when(resultSet.getInt("id")).thenReturn(user.getId());
    when(resultSet.getString("username")).thenReturn(user.getUsername());
    when(resultSet.getString("passwordhash")).thenReturn(user.getPasswordhash());

    // Act
    User u = sut.mapRow(resultSet, 1);

    // Assert
    assertAll(
            () -> assertEquals(user.getId(), u.getId()),
            () -> assertEquals(user.getUsername(), u.getUsername()),
            () -> assertEquals(user.getPasswordhash(), u.getPasswordhash())
    );
  }
}