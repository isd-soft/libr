package com.isd.libr.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.isd.libr.service.PersonService;
import com.isd.libr.web.dto.BookDto;
import com.isd.libr.web.dto.PersonDto;
import com.isd.libr.web.entity.Person;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/users")
@RequiredArgsConstructor
public class PersonController {
    private final PersonService personService;

    @GetMapping("/all")
    public List<PersonDto> listPerson() {
        return personService.findAll();
    }

    @PostMapping("/")
    public void savePerson(@RequestBody Person person){
        personService.save(person);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable ("id") long id){
        personService.deletePersonById(id);
    }
}
