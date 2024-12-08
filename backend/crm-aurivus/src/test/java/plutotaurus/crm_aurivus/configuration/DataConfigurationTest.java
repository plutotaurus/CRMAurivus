package plutotaurus.crm_aurivus.configuration;

import static org.junit.jupiter.api.Assertions.*;
import lombok.AllArgsConstructor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Import;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import javax.sql.DataSource;

@AllArgsConstructor
@SpringBootTest
@Import(DataConfiguration.class)
public class DataConfigurationTest {

  private ApplicationContext applicationContext;

  @DisplayName("Datascource loads")
  @Test
  public void testDataSourceBeanIsLoaded() {
    // Act
    DataSource dataSource = applicationContext.getBean(DataSource.class);

    // Assert
    assertNotNull(dataSource);
    assertInstanceOf(EmbeddedDatabase.class, dataSource);
  }
}

