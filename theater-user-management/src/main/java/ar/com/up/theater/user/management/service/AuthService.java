package ar.com.up.theater.user.management.service;

import ar.com.up.theater.user.management.dto.LoginRequest;
import ar.com.up.theater.user.management.dto.LoginResponse;
import ar.com.up.theater.user.management.dto.RegisterRequest;
import ar.com.up.theater.user.management.dto.RegistrationResponse;

public interface AuthService {
    LoginResponse login (LoginRequest loginRequest);

    RegistrationResponse register(RegisterRequest registrationRequest);
}
