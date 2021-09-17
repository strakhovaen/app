package rest.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import rest.app.dto.LoginDto;
import rest.app.service.CookieLoginServiceImpl;

@RestController
public class LoginController {

    private final CookieLoginServiceImpl service;

    @Autowired
    public LoginController(CookieLoginServiceImpl service) {
        this.service = service;
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginDto loginDto){
        String cookie = service.login(loginDto);
        if (cookie.isEmpty()) {
            return ResponseEntity.badRequest().build();
        } else {
            return ResponseEntity.ok(cookie);
        }
    }
}
