package com.isd.libr.service;

import com.isd.libr.web.dto.requests.RegisterRequest;
import com.isd.libr.web.entity.Person;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface AuthenticationService extends UserDetailsService {
    Person create(RegisterRequest request, String password);


    void updatePassword(long id, String hashedPassword) throws SamePasswordException;
}
