package plutotaurus.crm_aurivus.security;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.HashMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

/**
 * Class used to add users to the database. This class is used to add users to the database when the
 * application is started.
 */
@Component
public class UserMaker implements ApplicationRunner {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public UserMaker(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void run(ApplicationArguments args) {
        try {
            addUsersWithSalt();
        } catch (Exception e) {
            System.out.println("Error adding user");
        }
    }

    /**
     * Adds users to the database with hashed passwords and salts.
     *
     * @throws NoSuchAlgorithmException if the algorithm is not found
     * @throws InvalidKeySpecException if the key spec is invalid
     */
    public void addUsersWithSalt() throws NoSuchAlgorithmException, InvalidKeySpecException {
        String username1 = "usereen";
        String password1 = "user1!";
        String username2 = "usertwee";
        String password2 = "user2!";

        String email = "email";

        // Hash the passwords with the generated salts
        HashMap<String, String> hashAndSalt1 = JWTmaker.hashPassword(password1);
        HashMap<String, String> hashAndSalt2 = JWTmaker.hashPassword(password2);

        // Add the users to the database
        jdbcTemplate.update(
                "INSERT INTO Users (username, passwordhash, salt, email) VALUES (?, ?, ?, ?)",
                username1,
                hashAndSalt1.get("hash"),
                hashAndSalt1.get("salt"),
                email);
        jdbcTemplate.update(
                "INSERT INTO Users (username, passwordhash, salt, email) VALUES (?, ?, ?, ?)",
                username2,
                hashAndSalt2.get("hash"),
                hashAndSalt2.get("salt"),
                email);

        System.out.println(
                "Username: "
                        + username1
                        + ", Password: "
                        + password1
                        + ", hash: "
                        + hashAndSalt1.get("hash")
                        + ", salt: "
                        + hashAndSalt1.get("salt")
                        + ", email: "
                        + email);
        System.out.println(
                "Username: "
                        + username2
                        + ", Password: "
                        + password2
                        + ", hash: "
                        + hashAndSalt2.get("hash")
                        + ", salt: "
                        + hashAndSalt2.get("salt")
                        + ", email: "
                        + email);
        System.out.println("Users added");
    }
}
