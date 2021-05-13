package com.isd.libr.web.controller;

import com.isd.libr.service.PersonService;
import com.isd.libr.web.entity.Person;
import com.isd.libr.web.requests.RegisterRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/person")
public class PersonController {
    private final PersonService personService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public PersonController(PersonService personService,
                            PasswordEncoder passwordEncoder) {
        this.personService = personService;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/register")
    public ResponseEntity<?> createUser(@RequestBody RegisterRequest request) {
        String email = request.getEmail();
        String firstName = request.getFirstName();
        String lastName = request.getLastName();
        Integer age = request.getAge();
        String passwd = passwordEncoder.encode(request.getPasswd());
        String role = request.getRole();
        String phone = request.getPhone();
        Person user = personService.create(Person.builder()
                .email(email)
                .firstName(firstName)
                .lastName(lastName)
                .age(age)
                .passwd(passwd)
                .roles(role)
                .phone(phone)
                .build());

        return ResponseEntity.ok(user);
    }
}
