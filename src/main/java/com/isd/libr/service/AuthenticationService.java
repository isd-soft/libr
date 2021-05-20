package com.isd.libr.service;

import com.isd.libr.web.entity.Person;
import com.isd.libr.web.dto.requests.RegisterRequest;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface AuthenticationService extends UserDetailsService {
    Person create(RegisterRequest request, String password);


}
