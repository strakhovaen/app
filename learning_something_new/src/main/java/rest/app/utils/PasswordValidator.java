package rest.app.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import rest.app.dto.LoginDto;
import rest.app.model.User;
import rest.app.repository.UserRepository;

import java.util.NoSuchElementException;

@Component
public class PasswordValidator extends Validator<LoginDto> {

    private final UserRepository userRepository;

    @Autowired
    public PasswordValidator(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public boolean validate(LoginDto loginDto){
        try {
            User user = userRepository.getUserByUsername(loginDto.getUsername());
            return user.getPassword().equals(loginDto.getPassword());
        } catch (NoSuchElementException e){
            return false;
        }
    }
}
