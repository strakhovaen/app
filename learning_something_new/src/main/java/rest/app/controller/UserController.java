package rest.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rest.app.dto.RegistrationDto;
import rest.app.model.User;
import rest.app.service.UserService;

import java.util.List;

@RestController
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> getUsers(){
        return ResponseEntity.ok(userService.getUsers());
    }

    @PostMapping("/register")
    public ResponseEntity<String> signUp(@RequestBody RegistrationDto registrationDto) {
        try {
            userService.signUp(registrationDto);
            return ResponseEntity.ok().build();
        } catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
