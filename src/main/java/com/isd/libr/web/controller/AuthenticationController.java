package com.isd.libr.web.controller;

import com.isd.libr.service.AuthenticationService;
import com.isd.libr.web.dto.UserLoginDto;
import com.isd.libr.web.dto.requests.LoginRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService authenticationService;


    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        UserLoginDto authenticatedUser = authenticationService.authenticate(loginRequest);
        return ResponseEntity.ok(authenticatedUser);
    }




}
