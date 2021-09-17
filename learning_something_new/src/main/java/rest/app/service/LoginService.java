package rest.app.service;

import rest.app.dto.LoginDto;

public interface LoginService {
    String login(LoginDto loginDto);
}
