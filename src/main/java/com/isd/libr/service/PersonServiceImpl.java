package com.isd.libr.service;

import com.isd.libr.repo.PersonRepository;
import com.isd.libr.web.dto.PersonDto;
import com.isd.libr.web.dto.requests.UpdatePersonRequest;
import com.isd.libr.web.entity.Person;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
class PersonServiceImpl implements PersonService {
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

    @Override
    public void updateUser(long id, UpdatePersonRequest request) {
        Person person = personRepository.getById(id);
        person.setEmail(request.getEmail());
        person.setAge(request.getAge());
        person.setFirstName(request.getFirstName());
        person.setLastName(request.getLastName());
        person.setPhone(request.getPhone());
        person.setRole(request.getRole());
        personRepository.save(person);
    }
}
