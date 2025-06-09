package ar.com.up.theater.user.management.service;

import ar.com.up.theater.user.management.dto.LoginRequest;
import ar.com.up.theater.user.management.dto.LoginResponse;
import ar.com.up.theater.user.management.dto.RegisterRequest;
import ar.com.up.theater.user.management.dto.RegistrationResponse;
import ar.com.up.theater.user.management.exception.UserAlreadyExistsException;
import ar.com.up.theater.user.management.model.User;
import ar.com.up.theater.user.management.service.impl.AuthServiceImpl;
import ar.com.up.theater.user.management.util.JwtUtil;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.util.CollectionUtils;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AuthServiceImplTest {
    @Mock
    private AuthenticationManager authManager;
    @Mock
    private UserDetailsService userDetailsService;
    @Mock
    private UserService userService;
    @Mock
    private JwtUtil jwtUtil;

    @InjectMocks
    private AuthServiceImpl authService;

    private final String username = "gus";
    private final String password = "segura";
    private final String token = "fakeToken";

    @Test
    @DisplayName("login(): credenciales validas -> devuelve JWT")
    void loginSuccess_ReturnToken() {
        LoginRequest loginRequest = getLoginRequest();
        UserDetails user = org.springframework.security.core.userdetails.User.withUsername(username)
                .password(password)
                .roles("USER")
                .build();
        when(authManager.authenticate(
                new UsernamePasswordAuthenticationToken(username, password)
        )).thenReturn(mock(Authentication.class));
        when(userDetailsService.loadUserByUsername(anyString())).thenReturn(user);
        when(jwtUtil.generateToken(eq(user), anyMap())).thenReturn(token);

        LoginResponse response = authService.login(loginRequest);

        assertNotNull(response);
        assertFalse(CollectionUtils.isEmpty(response.getData()));
        assertEquals(response.getData().getFirst().getToken(), token);

        verify(authManager, times(1))
                .authenticate(argThat(auth ->
                        auth.getPrincipal().equals(username)
                                && auth.getCredentials().equals(password)
                ));
        verify(userDetailsService, times(1)).loadUserByUsername(username);
        verify(jwtUtil, times(1)).generateToken(eq(user), anyMap());
    }
    @Test
    @DisplayName("Register() -> Devuelvo datos usuario registrado")
    void registerSuccess_returnUserData(){

        RegisterRequest registrationRequest = getRegistrationRequest();
        User user = getUser();
        when(userService.createUser(any())).thenReturn(user);

        RegistrationResponse response = authService.register(registrationRequest);

        verify(userService, times(1)).createUser(any());

        assertNotNull(response);
        assertFalse(CollectionUtils.isEmpty(response.getData()));
        assertEquals(username, response.getData().getFirst().getUsername());
        assertEquals("John", response.getData().getFirst().getName());
        assertEquals("jhonnyBravo@cn.com", response.getData().getFirst().getEmail());
    }
    @Test
    @DisplayName("register(): usuario existente → lanza IllegalStateException")
    void registerUserExistsThrows() {

        RegisterRequest registrationRequest = getRegistrationRequest();
        User user = getUser();
        when(userService.createUser(any())).thenThrow(new UserAlreadyExistsException("REG01","UsuarioExiste"));

        RegistrationResponse response = authService.register(registrationRequest);

        assertNotNull(response);
        assertFalse(CollectionUtils.isEmpty(response.getErrors()));

        assertEquals("REG01", response.getErrors().getFirst().getCode());
    }

    private User getUser() {
        User user = new User();
        user.setUsername(username);
        user.setName("John");
        user.setEmail("jhonnyBravo@cn.com");
        return user;
    }

    private LoginRequest getLoginRequest() {
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUsername(username);
        loginRequest.setPassword(password);
        return loginRequest;
    }
    private RegisterRequest getRegistrationRequest() {
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setUsername(username);
        registerRequest.setPassword("IncreiblementeGuapo");
        registerRequest.setName("John");
        registerRequest.setEmail("jhonnyBravo@cn.com");
        return registerRequest;
    }
}