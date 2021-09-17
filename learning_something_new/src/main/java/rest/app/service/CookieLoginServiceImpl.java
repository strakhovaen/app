package rest.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rest.app.dto.LoginDto;
import rest.app.model.Role;
import rest.app.model.User;
import rest.app.repository.UserRepository;
import rest.app.utils.Validator;

import java.util.UUID;

@Service
public class CookieLoginServiceImpl implements LoginService {

    private final UserRepository userRepository;

    private final Validator<LoginDto> validator;

    @Autowired
    public CookieLoginServiceImpl(UserRepository userRepository, Validator<LoginDto> validator) {
        this.userRepository = userRepository;
        this.validator = validator;
    }

    public String login(LoginDto loginDto) {
        if (validator.validate(loginDto)) {

            User user = userRepository.getUserByUsername(loginDto.getUsername());

            if (user.getRole() == Role.ADMIN) {
                System.out.println("Welcome, admin!");
            }

            String cookie = UUID.randomUUID().toString();

            user.setLastCookie(cookie);

            userRepository.save(user);

            return cookie;
        } else {
            return null;
        }
    }
}
