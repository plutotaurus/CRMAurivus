package plutotaurus.crm_aurivus.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import plutotaurus.crm_aurivus.domain.JWT;
import plutotaurus.crm_aurivus.domain.UserLogin;
import plutotaurus.crm_aurivus.service.LoginService;

@RestController
@RequestMapping("login")
@CrossOrigin(origins = "http://localhost:5173", allowCredentials = "true")
public class LoginController {

    private final LoginService loginService;

    @Autowired
    public LoginController(final LoginService loginService){
        this.loginService = loginService;
    }

    @PostMapping
    public ResponseEntity<JWT> login(@RequestBody UserLogin userLogin){

        String token = loginService.login(userLogin.getUsername());
        return ResponseEntity.ok().body(new JWT(token));

    }
}

