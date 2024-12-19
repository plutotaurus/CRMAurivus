package plutotaurus.crm_aurivus.domain;

import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Error {
  private String message;

  @Override
  public String toString() {
    return Map.of("message", message).toString();
  }
}
