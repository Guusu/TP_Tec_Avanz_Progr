package ar.com.up.theater.user.management.service.impl;

import ar.com.up.theater.user.management.dto.RegisterRequest;
import ar.com.up.theater.user.management.exception.UserAlreadyExistsException;
import ar.com.up.theater.user.management.exception.UserRegistrationException;
import ar.com.up.theater.user.management.model.User;
import ar.com.up.theater.user.management.repository.UserRepository;
import ar.com.up.theater.user.management.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder encoder;
    Logger logger  = LoggerFactory.getLogger(UserServiceImpl.class);
    public UserServiceImpl(PasswordEncoder encoder, UserRepository userRepository) {
        this.userRepository = userRepository;
        this.encoder = encoder;
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username).orElse(null);
    }

    public User createUser(RegisterRequest registerRequest) throws UserAlreadyExistsException, UserRegistrationException {

        User user = userRepository.findByUsername(registerRequest.getUsername()).orElse(null);
        User newUser;
        if (user != null) {
            throw new UserAlreadyExistsException("REG01", "El Usuariuo ya Existe");
        }
        try {
            newUser = new User();
            newUser.setUsername(registerRequest.getUsername());
            newUser.setPassword(encoder.encode(registerRequest.getPassword()));
            newUser.setEmail(registerRequest.getEmail());
            newUser.setName(registerRequest.getName());
            userRepository.save(newUser);

        } catch (Exception e) {
            logger.info(e.getMessage());
            throw new UserRegistrationException("REG00", "Error Al intentar Registrar el Usuario", e.getMessage());
        }
        return newUser;
    }
}
