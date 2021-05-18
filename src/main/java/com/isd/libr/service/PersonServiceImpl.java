package com.isd.libr.service;

import com.isd.libr.repo.PersonRepository;
import com.isd.libr.web.dto.PersonDto;
import com.isd.libr.web.entity.Person;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PersonServiceImpl implements PersonService {
    private final PersonRepository personRepository;

    @Override
    public List<PersonDto> findAll() {
        return personRepository.findAll().stream().map(PersonDto::from).collect(Collectors.toList());
    }

    @Override
    public void save(Person person) {
        personRepository.save(person);
    }

    @Override
    public void deletePersonById(long id) {

        personRepository.deleteById(id);
    }
}
