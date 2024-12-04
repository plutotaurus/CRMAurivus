package plutotaurus.crm_aurivus.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class User {
    private int id;
    private String username;
    private String passwordhash;
}
