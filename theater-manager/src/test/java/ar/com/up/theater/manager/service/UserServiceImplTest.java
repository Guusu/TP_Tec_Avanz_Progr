package ar.com.up.theater.manager.service;

import ar.com.up.theater.manager.model.Artist;
import ar.com.up.theater.manager.model.User;
import ar.com.up.theater.manager.repository.UserRepository;
import ar.com.up.theater.manager.service.impl.UserServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {
    @Mock
    UserRepository userRepository;
    @InjectMocks
    UserServiceImpl userService;

    @Test
    @DisplayName("getUserByName --> Ok")
    void getUserByName() {
        User user = new User();
        user.setName("John");
        user.setUsername("username");
        when(userRepository.findByUsernameIgnoreCase(anyString())).thenReturn(Optional.of(user));

        User userByUsername = userService.getUserByUsername(user.getName());

        assertNotNull(userByUsername);
        assertEquals(user, userByUsername);

    }


}