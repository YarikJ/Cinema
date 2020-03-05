package dev.cinema.controllers;

import dev.cinema.models.dto.UserRequestDto;
import dev.cinema.service.AuthenticationService;

import javax.validation.Valid;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationController {
    private AuthenticationService authenticationService;
    private PasswordEncoder passwordEncoder;

    public AuthenticationController(AuthenticationService authenticationService,
                                    PasswordEncoder passwordEncoder) {
        this.authenticationService = authenticationService;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/register")
    public void register(@RequestBody @Valid UserRequestDto userRequestDto) {
        authenticationService.register(userRequestDto.getEmail(),
                passwordEncoder.encode(userRequestDto.getPassword()));
    }
}
