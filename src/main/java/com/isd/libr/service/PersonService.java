package com.isd.libr.service;

import com.isd.libr.web.dto.PersonDto;
import com.isd.libr.web.dto.requests.UpdatePersonRequest;
import com.isd.libr.web.entity.Person;

import java.util.List;

public interface PersonService {
    List<PersonDto> findAll();

    void save(Person person);

    void deletePersonById(long id);

    void updateUser(long id, UpdatePersonRequest request);
}
