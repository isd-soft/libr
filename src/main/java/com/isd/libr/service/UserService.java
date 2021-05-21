package com.isd.libr.service;

import com.isd.libr.web.dto.UserDto;
import com.isd.libr.web.dto.requests.UpdatePersonRequest;
import com.isd.libr.web.entity.User;

import java.util.List;

public interface UserService {
    List<UserDto> findAll();

    void save(User user);

    void deletePersonById(long id);

    void updateUser(long id, UpdatePersonRequest request);
}
