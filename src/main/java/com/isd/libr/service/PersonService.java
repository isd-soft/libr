package com.isd.libr.service;

import com.isd.libr.web.dto.PersonDto;
import com.isd.libr.web.entity.Person;

import java.util.List;
import java.util.Optional;

public interface PersonService {
    List<PersonDto> findAll();

    void save(Person person);

    void deletePersonById(Long id);
}
