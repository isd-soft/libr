package com.isd.libr.service;


import com.isd.libr.repo.PersonRepository;
import com.isd.libr.web.dto.BookDto;
import com.isd.libr.web.dto.PersonDto;
import com.isd.libr.web.entity.Book;
import com.isd.libr.web.entity.Person;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PersonServiceImpl implements PersonService{
    private PersonRepository personRepository;

    @Override
    public List<PersonDto> findAll() {
        List<Person> personList =  personRepository.findAll();
        List<PersonDto> personDtoList = new ArrayList<>();
        for (int i = 0; i < personList.size(); i++) {
            personDtoList.add(convertToPersonDto(personList.get(i)));
        }
        return personDtoList;
    }

    @Override
    public void save(Person person) {
        personRepository.save(person);

    }

    @Override
    public void deletePersonById(Long id) {
        personRepository.deleteById(id);

    }

    @Override
    public Optional<PersonDto> findPersonByEmail(String email) {
        Optional<Person> personByEmail =  personRepository.findByEmail(email);
        return personByEmail.map(this::convertToPersonDto);
    }

    public PersonDto convertToPersonDto(Person person) {
        PersonDto personDto = new PersonDto();
        personDto.setFullName(person.getFirstName() + person.getLastName());
        personDto.setEmail(person.getEmail());
        personDto.setPhone(person.getPhone());
        return personDto;
    }
}
