package ar.com.up.theater.user.management.controller;

import ar.com.up.theater.user.management.dto.LoginRequest;
import ar.com.up.theater.user.management.dto.LoginResponse;
import ar.com.up.theater.user.management.dto.RegisterRequest;
import ar.com.up.theater.user.management.dto.RegistrationResponse;
import ar.com.up.theater.user.management.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/user")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login (@RequestBody LoginRequest loginRequest){

        LoginResponse loginResponse = authService.login(loginRequest);

        if(!CollectionUtils.isEmpty(loginResponse.getError()))
            return new ResponseEntity<>(loginResponse,BAD_REQUEST);

        return ResponseEntity.ok(loginResponse);
    }

    @PostMapping("/register")
    public ResponseEntity<RegistrationResponse> register(@RequestBody RegisterRequest registrationRequest) {

        RegistrationResponse response = authService.register(registrationRequest);
        if(!CollectionUtils.isEmpty(response.getErrors()))
            return new ResponseEntity<>(response,BAD_REQUEST);

        return new ResponseEntity<>(response,CREATED);
    }
}
