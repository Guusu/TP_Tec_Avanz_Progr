package ar.com.up.theater.user.management.service.impl;

import ar.com.up.theater.user.management.dto.*;
import ar.com.up.theater.user.management.exception.UserAlreadyExistsException;
import ar.com.up.theater.user.management.exception.UserRegistrationException;
import ar.com.up.theater.user.management.model.User;
import ar.com.up.theater.user.management.repository.UserRepository;
import ar.com.up.theater.user.management.service.AuthService;
import ar.com.up.theater.user.management.service.UserService;
import ar.com.up.theater.user.management.util.JwtUtil;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Map;

@Service
public class AuthServiceImpl implements AuthService {

    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;
    private final UserService userService;

    public AuthServiceImpl(JwtUtil jwtUtil, AuthenticationManager authenticationManager, UserDetailsService userDetailsService, PasswordEncoder passwordEncoder, UserRepository userRepository, UserService userService) {
        this.jwtUtil = jwtUtil;
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
        this.userService = userService;
    }


    @Override
    public LoginResponse login(LoginRequest loginRequest) {

        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setData(new ArrayList<>());
        loginResponse.setError(new ArrayList<>());
        JwtResponseInfo jwtResponseInfo = new JwtResponseInfo();

        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        UserDetails userDetails = userDetailsService.loadUserByUsername(loginRequest.getUsername());

        Map<String,Object> extras = Map.of("roles",userDetails.getAuthorities());

        jwtResponseInfo.setToken(jwtUtil.generateToken(userDetails,extras));

        loginResponse.getData().add(jwtResponseInfo);

        return loginResponse;
    }

    @Override
    public RegistrationResponse register(RegisterRequest registrationRequest) {

        RegistrationResponse response = new RegistrationResponse();
        response.setData(new ArrayList<>());
        response.setErrors(new ArrayList<>());

        try {
            User user =  userService.createUser(registrationRequest);
            UserRegistrationData userRegistrationData = new UserRegistrationData();
            userRegistrationData.setUsername(user.getUsername());
            userRegistrationData.setEmail(user.getEmail());
            userRegistrationData.setName(user.getName());
            userRegistrationData.setMessage("Usuario Creaado Exitosamente");
            response.getData().add(userRegistrationData);
        } catch (UserAlreadyExistsException | UserRegistrationException e) {
            ErrorDto errorDto = new ErrorDto();
            errorDto.setCode(e.getCode());
            errorDto.setMessage(e.getMessage());
            response.getErrors().add(errorDto);
        }
        return response;

    }

  }
