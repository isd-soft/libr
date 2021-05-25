package com.isd.libr.web.controller;

import com.isd.libr.service.AuthenticationService;
import com.isd.libr.service.SamePasswordException;
import com.isd.libr.service.TokenService;
import com.isd.libr.web.dto.UserLoginDto;
import com.isd.libr.web.dto.requests.LoginRequest;
import com.isd.libr.web.dto.requests.RegisterRequest;
import com.isd.libr.web.dto.requests.UpdatePasswordRequest;
import com.isd.libr.web.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService authenticationService;
    private final PasswordEncoder passwordEncoder;


    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        UserLoginDto authenticatedUser = authenticationService.authenticate(loginRequest);
        return ResponseEntity.ok(authenticatedUser);
    }




}
