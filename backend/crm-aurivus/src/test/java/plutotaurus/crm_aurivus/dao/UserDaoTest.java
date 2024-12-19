package plutotaurus.crm_aurivus.dao;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import plutotaurus.crm_aurivus.dao.rowmapper.UserRowMapper;
import plutotaurus.crm_aurivus.domain.User;
import plutotaurus.crm_aurivus.exceptions.AuthenticationException;

@ExtendWith(MockitoExtension.class)
class UserDaoTest {
    private final User user = new User(1, "username", "password");
    @Mock private JdbcTemplate jdbcTemplate;
    @InjectMocks private  UserDao sut;

    @Test
    void testFindUserByUsernameSuccessful() {
        //Arrange
        when(jdbcTemplate.queryForObject(eq("SELECT * FROM Users WHERE username = ?"), any(UserRowMapper.class), eq(user.getUsername()))).thenReturn(user);

        //Act
        User result = sut.findUserByUsername(user.getUsername());

        //Assert
        assertAll(
                () -> verify(jdbcTemplate).queryForObject(eq("SELECT * FROM Users WHERE username = ?"), any( UserRowMapper.class), eq(user.getUsername())),
        () -> assertEquals(user, result)
        );
      }
      @Test
      void testFindUserByUsernameUnsuccessful(){
          //Arrange
       when(jdbcTemplate.queryForObject(eq("SELECT * FROM Users WHERE username = ?"), any(UserRowMapper.class), eq(user.getUsername()))).thenThrow(EmptyResultDataAccessException.class);

    // Act
    AuthenticationException exception =
        assertThrows(
            AuthenticationException.class, () -> sut.findUserByUsername(user.getUsername()));

          //Assert
          assertAll(
                  () -> verify(jdbcTemplate).queryForObject(eq("SELECT * FROM Users WHERE username = ?"), any( UserRowMapper.class), eq(user.getUsername())),
                  () -> assertEquals("Invalid username or password", exception.getMessage())
          );
      }

    @Test
    void testCreate() {
        //Act
        sut.create(user);

        //Assert
        assertAll(
                () -> verify(jdbcTemplate).update(eq("INSERT INTO Users (username, passwordhash) VALUES (?, ?)"), eq(user.getUsername()), eq(user.getPasswordhash()))
        );
      }
}