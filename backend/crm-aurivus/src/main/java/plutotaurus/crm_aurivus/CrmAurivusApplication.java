package plutotaurus.crm_aurivus;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class CrmAurivusApplication {

	public static void main(String[] args) {
		SpringApplication.run(CrmAurivusApplication.class, args);
	}

}
