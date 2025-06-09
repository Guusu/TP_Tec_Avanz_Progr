package ar.com.up.theater.user.management.service;

import ar.com.up.theater.user.management.dto.RegisterRequest;
import ar.com.up.theater.user.management.exception.UserAlreadyExistsException;
import ar.com.up.theater.user.management.exception.UserRegistrationException;
import ar.com.up.theater.user.management.model.User;

public interface UserService {
    User findByUsername(String username);
    User createUser(RegisterRequest registerRequest) throws UserAlreadyExistsException, UserRegistrationException;
}
