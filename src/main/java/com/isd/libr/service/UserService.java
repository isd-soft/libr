package com.isd.libr.service;

import com.isd.libr.web.dto.UserDTO;
import com.isd.libr.web.entity.Person;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserService {
    private final UserDTO userDTO;

    @Autowired
    public UserService(UserDTO userDTO) {
        this.userDTO = userDTO;
    }

    public Person create(Person user) {
        return userDTO.save(user);
    }
}
