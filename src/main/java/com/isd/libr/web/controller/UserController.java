package com.isd.libr.web.controller;

import com.isd.libr.service.UserService;
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
public class UserController {
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserController(UserService userService,
                          PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/register")
    public ResponseEntity<?> createUser(@RequestBody RegisterRequest request) {
        String email = request.getEmail();
        String firstName = request.getFirstName();
        String lastName = request.getLastName();
        Integer age = request.getAge();
        String passwd = passwordEncoder.encode(request.getPasswd());
        List<String> roles = request.getRoles();
        String phone = request.getPhone();
        Person user = userService.create(Person.builder()
                .email(email)
                .firstName(firstName)
                .lastName(lastName)
                .age(age)
                .passwd(passwd)
                .roles(roles)
                .phone(phone)
                .build());

        return ResponseEntity.ok(user);
    }
}
