package rest.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rest.app.dto.RegistrationDto;
import rest.app.model.Role;
import rest.app.model.User;
import rest.app.repository.UserRepository;
import rest.app.utils.Validator;

import javax.xml.bind.ValidationException;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class UserService {
    private final UserRepository userRepository;

    private final Validator<RegistrationDto> validator;

    @Autowired
    public UserService(UserRepository userRepository, Validator<RegistrationDto> validator) {
        this.userRepository = userRepository;
        this.validator = validator;
    }

    public List<User> getUsers(){
        return userRepository.getUserList();
    }

    public void signUp(RegistrationDto registrationDto) throws Exception {
        if (validator.validate(registrationDto)) {
            User fUser;
            try {
                fUser = userRepository.getUserByUsername(registrationDto.getUsername());
            } catch (NoSuchElementException e){
                fUser = null;
            }
            if (fUser == null) {
                System.out.println("2");
                User user = User.builder()
                        .id(userRepository.getUserList().size())
                        .username(registrationDto.getUsername())
                        .password(registrationDto.getPassword())
                        .lastCookie("")
                        .role(Role.USER)
                        .build();
                System.out.println(user.toString());
                userRepository.save(user);
            } else {
                System.out.println("3");
                throw new Exception();
            }
        } else {
            System.out.println("4");
            throw new ValidationException("Not valid username or password");
        }
    }
}
