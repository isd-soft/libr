package com.isd.libr.service;


import com.isd.libr.repo.UserRepository;
import com.isd.libr.web.dto.UserLoginDto;
import com.isd.libr.web.dto.requests.LoginRequest;
import com.isd.libr.web.dto.requests.RegisterRequest;
import com.isd.libr.web.dto.requests.UpdatePasswordRequest;
import com.isd.libr.web.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
class AuthenticationServiceImpl implements AuthenticationService {

    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;

    @Override
    public UserLoginDto authenticate(LoginRequest loginRequest) throws AuthenticationFailedException {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getEmail(),
                        loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        if (authentication.isAuthenticated()) {
            User user = (User) authentication.getPrincipal();
            return UserLoginDto.from(user, tokenService.createToken(user));
        } else {
            throw new AuthenticationFailedException("Couldn't authenticate this user");
        }
    }


}
