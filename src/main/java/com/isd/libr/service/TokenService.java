package com.isd.libr.service;

import com.isd.libr.web.entity.Person;
import org.springframework.security.core.Authentication;

import java.util.Map;

public interface TokenService {
    String createToken(Person person);

    Map<String, String> getUserDataFromToken(String token);

    Authentication getAuthentication(String token);
}
