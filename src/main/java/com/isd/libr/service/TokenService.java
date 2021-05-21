package com.isd.libr.service;

import com.isd.libr.web.entity.User;
import org.springframework.security.core.Authentication;

import java.util.Map;

public interface TokenService {
    String createToken(User user);

    Map<String, String> getUserDataFromToken(String token);

    Authentication getAuthentication(String token);
}
