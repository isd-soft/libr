package com.isd.libr.service;

import com.isd.libr.web.dto.UserDto;
import com.isd.libr.web.dto.requests.UpdateUserRequest;
import com.isd.libr.web.entity.User;

import java.util.List;

public interface UserService {
    List<UserDto> findAll();

    void save(User user);

    User getById(long id);

    void deleteUserById(long id);

    void updateUser(long id, UpdateUserRequest request);
}
