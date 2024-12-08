package plutotaurus.crm_aurivus.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Map;

@Getter
@AllArgsConstructor
public class Error {
    private String message;

    @Override
    public String toString() {
        return Map.of("message",message).toString();
    }
}
