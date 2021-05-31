package com.isd.libr.service;

import com.isd.libr.web.dto.UserDto;
import com.isd.libr.web.dto.requests.RegisterRequest;
import com.isd.libr.web.dto.requests.UpdatePasswordRequest;
import com.isd.libr.web.dto.requests.UpdateUserRequest;
import com.isd.libr.web.entity.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;
import java.util.Optional;

public interface UserService extends UserDetailsService {
    List<UserDto> findAll();

    User create(RegisterRequest request);

    void updatePassword(long id, UpdatePasswordRequest request) throws SamePasswordException;

    Optional<User> findById(long id);

    void deleteUserById(long id);

    void updateUser(long id, UpdateUserRequest request);

    UserDetails loadUserByUsername(String email) throws UsernameNotFoundException;

    Integer countUsers();
}
