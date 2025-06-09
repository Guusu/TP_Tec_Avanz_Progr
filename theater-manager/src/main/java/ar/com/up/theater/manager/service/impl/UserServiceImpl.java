package ar.com.up.theater.manager.service.impl;

import ar.com.up.theater.manager.model.User;
import ar.com.up.theater.manager.repository.UserRepository;
import ar.com.up.theater.manager.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User getUserByUsername(String username) {

        return userRepository.findByUsernameIgnoreCase(username).orElse(null);
    }
}
