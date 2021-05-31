package com.isd.libr.service;

import com.isd.libr.web.dto.UserLoginDto;
import com.isd.libr.web.dto.requests.LoginRequest;
import com.isd.libr.web.entity.User;

/**
 * AuthenticationService is an interface for authentication and authorization purposes.
 *
 * <p>Preferred implementation {@code AuthenticationServiceImpl}</p>
 *
 * @author Grosu Kirill
 * @see org.springframework.security.authentication.AuthenticationManager
 */
public interface AuthenticationService {
    /**
     * Used to authenticate a user by providing credentials.
     *
     * @param loginRequest object of type {@link LoginRequest} containing fields: <ul>
     *                     <li>Email</li>
     *                     <li>Password</li>
     *                     </ul>
     * @return authenticated {@link User} converted into Data Transfer Object.
     * @throws AuthenticationFailedException in case when wrong credentials were introduced.
     */
    UserLoginDto authenticate(LoginRequest loginRequest) throws AuthenticationFailedException;

}
