package plutotaurus.crm_aurivus.controller;

import io.jsonwebtoken.Claims;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import plutotaurus.crm_aurivus.service.JwtService;


@RestController
@AllArgsConstructor
@RequestMapping("contactfile")
@CrossOrigin(origins = "http://localhost:5173", allowCredentials = "true")
public class ContactFileController {

    private final JwtService jwtService;
    @GetMapping("/test")
    ResponseEntity<Claims> test(@RequestHeader("Authorization") String authorizationHeader){
        Claims claims = jwtService.validateToken(authorizationHeader);
        return ResponseEntity.ok(claims);
    }

}
