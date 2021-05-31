package com.isd.libr.service;

import com.isd.libr.web.entity.User;

import java.util.Map;

/**
 * TokenService is an interface for Token purposes.
 *
 * <p>Preferred implementation {@code TokenServiceImpl}</p>
 *
 * @author Grosu Kirill
 * @see com.isd.libr.config.JwtFilter
 */
public interface TokenService {
    /**
     * Returns Email and ID of a user by providing unique token.
     *
     * @param token unique identifier that is used to retrieve data from it
     * @return Map of String Email or ID and the value being Email or ID retrieved from token
     */
    Map<String, String> getUserDataFromToken(String token);

    /**
     * Creates a unique token using HMAC256 algorithm.
     *
     * @param user being a material that then is passed to JWT.create as a claim.
     * @return unique created token.
     */
    String createToken(User user);
}
