package rest.app.utils;

import org.springframework.stereotype.Component;
import rest.app.dto.RegistrationDto;

@Component
public class UserRegistrationValidator extends Validator<RegistrationDto> {

    public boolean validate(RegistrationDto registrationDto){

        String username = registrationDto.getUsername();
        String password = registrationDto.getPassword();
        System.out.println(!username.contains(" ") && !password.contains(" "));
        return !username.contains(" ") && !password.contains(" ");
    }
}
