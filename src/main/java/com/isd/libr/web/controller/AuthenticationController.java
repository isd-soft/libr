package com.isd.libr.web.controller;

import com.isd.libr.repo.BookRepository;
import com.isd.libr.repo.UserRepository;
import com.isd.libr.service.AuthenticationService;
import com.isd.libr.service.SamePasswordException;
import com.isd.libr.service.TokenService;
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
    private final AuthenticationManager authenticationManager;
    private final AuthenticationService authenticationService;
    private final TokenService tokenService;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    @PostMapping("/register")
    public ResponseEntity<?> createUser(@RequestBody RegisterRequest request) {
        String passwd = passwordEncoder.encode(request.getPasswd());
        User user = authenticationService.create(request, passwd);
        return ResponseEntity.ok(user);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        if (authentication.isAuthenticated()) {
            User user = (User) authentication.getPrincipal();
            HttpHeaders headers = new HttpHeaders();
            headers.add("Bearer", tokenService.createToken(user));
            return ResponseEntity.ok().headers(headers).body(user);
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    @PutMapping("/password/{id}")
    public ResponseEntity<?> updatePassword(@PathVariable("id") long id, @RequestBody UpdatePasswordRequest updatePasswordRequest) {
        try {
            User user = userRepository.getById(id);
            if (passwordEncoder.matches(updatePasswordRequest.getNewPassword(),user.getPassword())) {
                throw new SamePasswordException("New and old passwords must be different");
            }
            String hashedPassword = passwordEncoder.encode(updatePasswordRequest.getNewPassword());
            authenticationService.updatePassword(id, hashedPassword);
        } catch (SamePasswordException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        return ResponseEntity.ok().build();
    }




}
