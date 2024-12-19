package plutotaurus.crm_aurivus.exceptions;

import lombok.Getter;
import plutotaurus.crm_aurivus.domain.Error;

@Getter
public class CustomException extends RuntimeException {

  private final Error error;

  public CustomException(String message) {
    super(message);
    this.error = new Error(message);
  }
}
