package com.isd.libr.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.isd.libr.web.entity.Person;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
class TokenServiceImpl implements TokenService {

    @Value("${jwt.token.secret")
    private String TOKEN_SECRET;
    @Getter
    @Value("${jwt.token.validity}")
    private Integer TOKEN_VALIDITY;

    private final AuthenticationServiceImpl authenticationService;

    @Autowired
    public TokenServiceImpl(AuthenticationServiceImpl authenticationService) {
        this.authenticationService = authenticationService;
    }

    public String createToken(Person person) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);
            Date now = new Date();
            return "Bearer " + JWT.create()
                    .withClaim("username", person.getUsername())
                    .withClaim("userId", person.getId().toString())
                    .withExpiresAt(new Date(now.getTime() + TOKEN_VALIDITY))
                    .sign(algorithm);
        } catch (JWTCreationException exception) {
            exception.printStackTrace();
        }
        return null;
    }


    public Map<String, String> getUserDataFromToken(String token) {
        Map<String, String> result = new HashMap<>();
        try {
            Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);
            JWTVerifier verifier = JWT.require(algorithm).build();
            DecodedJWT jwt = verifier.verify(token);
            result.put("email", jwt.getClaim("username").asString());
            result.put("id", jwt.getClaim("userId").asString());
            return result;
        } catch (JWTVerificationException exception) {
            exception.printStackTrace();
            return null;
        }
    }

    public Authentication getAuthentication(String token) {
        Map<String, String> userData = getUserDataFromToken(token);
        UserDetails userDetails = authenticationService.loadUserByUsername(userData.get("email"));
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

}
