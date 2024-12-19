package plutotaurus.crm_aurivus.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import plutotaurus.crm_aurivus.domain.JWT;
import plutotaurus.crm_aurivus.domain.UserLogin;
import plutotaurus.crm_aurivus.service.UserService;

@RestController
@AllArgsConstructor
@RequestMapping("auth")
@CrossOrigin(origins = "http://localhost:5173", allowCredentials = "true")
public class AuthenticatorController {

  private final UserService userService;

  @PostMapping("/login")
  public ResponseEntity<JWT> login(@RequestBody UserLogin userLogin) {
    String token = userService.login(userLogin);
    return ResponseEntity.ok().body(new JWT(token));
  }
}
