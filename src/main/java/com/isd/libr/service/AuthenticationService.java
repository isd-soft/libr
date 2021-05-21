package com.isd.libr.service;

import com.isd.libr.web.dto.requests.RegisterRequest;
import com.isd.libr.web.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface AuthenticationService extends UserDetailsService {
    User create(RegisterRequest request, String password);


    void updatePassword(long id, String hashedPassword) throws SamePasswordException;
}
