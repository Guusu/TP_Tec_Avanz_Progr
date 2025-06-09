package ar.com.up.theater.user.management.service;

import ar.com.up.theater.user.management.dto.LoginRequest;
import ar.com.up.theater.user.management.dto.RegisterRequest;
import ar.com.up.theater.user.management.exception.UserAlreadyExistsException;
import ar.com.up.theater.user.management.model.User;
import ar.com.up.theater.user.management.repository.UserRepository;
import ar.com.up.theater.user.management.service.impl.UserServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;
    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserServiceImpl userService;

    private final String username = "gus";
    private final String password = "segura";
    private final String hashPass = "HASHED";
    @Test
    void findByUsername() {
    }

    @Test
    @DisplayName("CreateUser --> Ok ")
    void createUser() {
        RegisterRequest registrationRequest = getRegistrationRequest();
        when(userRepository.findByUsername(username)).thenReturn(Optional.empty());
        when(passwordEncoder.encode(password)).thenReturn(hashPass);

        assertDoesNotThrow(() -> userService.createUser(registrationRequest));

        ArgumentCaptor<User> cap = ArgumentCaptor.forClass(User.class);
        verify(userRepository).save(cap.capture());
        User saved = cap.getValue();
        assertEquals(username, saved.getUsername());
        assertEquals(hashPass, saved.getPassword());
    }

    @Test
    @DisplayName("CreateUser: usuario existe → lanza UserAlreadyExistsException")
    void createUser_AlreadyExists() {
        RegisterRequest registrationRequest = getRegistrationRequest();

        when(userRepository.findByUsername(username))
                .thenReturn(Optional.of(new User()));

        UserAlreadyExistsException ex = assertThrows(
                UserAlreadyExistsException.class,
                () -> userService.createUser(registrationRequest)
        );
        assertEquals("REG01", ex.getCode());
        verify(userRepository, never()).save(any());
    }
    private User getUser() {
        User user = new User();
        user.setUsername(username);
        user.setName("John");
        user.setEmail("jhonnyBravo@cn.com");
        return user;
    }
    private RegisterRequest getRegistrationRequest() {
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setUsername(username);
        registerRequest.setPassword(password);
        registerRequest.setName("John");
        registerRequest.setEmail("jhonnyBravo@cn.com");
        return registerRequest;
    }
    @Test
    @DisplayName("findByUsername: usuario no existe --> devuelve null")
    void findByUsernameNotFound() {
        when(userRepository.findByUsername(username)).thenReturn(Optional.empty());

        User byUsername = userService.findByUsername(username);

        assertNull(byUsername);

    }

    @Test
    @DisplayName("findByUsernameOrFail: usuario existe → devuelve entidad")
    void findByUsernameOrFailSuccess() {
        User u = getUser();
        u.setUsername("ana");
        when(userRepository.findByUsername("ana")).thenReturn(Optional.of(u));

        User result = userService.findByUsername("ana");
        assertSame(u, result);
    }
}