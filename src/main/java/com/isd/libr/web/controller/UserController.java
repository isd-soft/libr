package com.isd.libr.web.controller;

import com.isd.libr.service.UserService;
import com.isd.libr.web.dto.UserDto;
import com.isd.libr.web.dto.requests.UpdatePersonRequest;
import com.isd.libr.web.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/all")
    public List<UserDto> listPerson() {
        return userService.findAll();
    }

    @PostMapping("/")
    public void savePerson(@RequestBody User user) {
        userService.save(user);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable ("id") long id){
        userService.deletePersonById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(@PathVariable("id") long id, @RequestBody UpdatePersonRequest request) {
        userService.updateUser(id, request);
        return ResponseEntity.ok().build();
    }
}
