package ar.com.up.theater.user.management.controller;

import ar.com.up.theater.user.management.dto.*;
import ar.com.up.theater.user.management.service.AuthService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AuthControllerTest {

    @Mock
    private AuthService authService;

    @InjectMocks
    private AuthController authController;

    private final String fakeJwt = "fakeToken";
    private final String username = "jhonnyBravo";

    @Test
    @DisplayName("Login() -> ResponseEntity con token y 200 OK")
    void login() {
        LoginRequest loginRequest = getLoginRequest();

        LoginResponse response = new LoginResponse();
        response.setData(new ArrayList<>());
        JwtResponseInfo jwt = new JwtResponseInfo();
        jwt.setToken(fakeJwt);
        response.getData().add(jwt);
        when(authService.login(loginRequest)).thenReturn(response);

        ResponseEntity<LoginResponse> login = authController.login(loginRequest);

        assertEquals(200,login.getStatusCode().value());
        assertNotNull(login.getBody());
        assertFalse(CollectionUtils.isEmpty(login.getBody().getData()));
        assertEquals(fakeJwt,login.getBody().getData().getFirst().getToken());

        verify(authService,times(1)).login(any());

    }
    @Test
    @DisplayName("RegisterUser() -> 201 CREATED")
    void registerUser_ReturnCreated(){
        RegisterRequest registrationRequest = getRegistrationRequest();
        RegistrationResponse registrationResponse = getRegistrationResponse();

        when(authService.register(registrationRequest)).thenReturn(registrationResponse);

        ResponseEntity<RegistrationResponse> response = authController.register(registrationRequest);

        assertEquals(201, response.getStatusCode().value());
        assertNotNull(response.getBody());
        assertFalse(CollectionUtils.isEmpty(response.getBody().getData()));
        assertEquals(username,response.getBody().getData().getFirst().getUsername());

        verify(authService,times(1)).register(registrationRequest);

    }

    private RegistrationResponse getRegistrationResponse() {
        RegistrationResponse response = new RegistrationResponse();
        UserRegistrationData userRegistrationData = new UserRegistrationData();
        userRegistrationData.setUsername(username);
        response.setData(new ArrayList<>());
        response.getData().add(userRegistrationData);
        return response;
    }

    private RegisterRequest getRegistrationRequest() {
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setUsername(username);
        registerRequest.setPassword("IncreiblementeGuapo");
        registerRequest.setName("John");
        registerRequest.setEmail("jhonnyBravo@cn.com");
        return registerRequest;
    }

    private static LoginRequest getLoginRequest() {
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUsername("admin");
        loginRequest.setPassword("admin");
        return loginRequest;
    }

    @Test
    @DisplayName("Login() -> Error de Credenciales Incorrectas y 200 OK")
    void loginError() {
        LoginRequest loginRequest = getLoginRequest();

        LoginResponse response = new LoginResponse();
        response.setData(new ArrayList<>());
        ErrorDto error = new ErrorDto();
        error.setCode("LOG01");
        error.setMessage("Credenciales incorrectas");
        response.setError(Collections.singletonList(error));
        when(authService.login(loginRequest)).thenReturn(response);

        ResponseEntity<LoginResponse> login = authController.login(loginRequest);

        assertEquals(400,login.getStatusCode().value());
        assertNotNull(login.getBody());
        assertFalse(CollectionUtils.isEmpty(login.getBody().getError()));
        assertEquals("LOG01",login.getBody().getError().getFirst().getCode());


    }
}