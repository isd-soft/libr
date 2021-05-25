package com.isd.libr.service;

import com.isd.libr.web.dto.UserLoginDto;
import com.isd.libr.web.dto.requests.LoginRequest;
import com.isd.libr.web.dto.requests.RegisterRequest;
import com.isd.libr.web.dto.requests.UpdatePasswordRequest;
import com.isd.libr.web.entity.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface AuthenticationService {

    UserLoginDto authenticate(LoginRequest loginRequest) throws AuthenticationFailedException;

}
