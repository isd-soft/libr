
package com.isd.libr.web.controller;

import com.isd.libr.service.UserService;
import com.isd.libr.web.dto.UserDto;
import com.isd.libr.web.dto.requests.UpdateUserRequest;
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

    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable("id") Long id) {
        User user = userService.getById(id);
        UserDto userDto = UserDto.from(user);
        return ResponseEntity.ok(userDto);
    }

    @GetMapping("/all")
    public List<UserDto> listUser() {
        return userService.findAll();
    }

    @PostMapping("/")
    public void saveUser(@RequestBody User user) {
        userService.save(user);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable ("id") long id){
        userService.deleteUserById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(@PathVariable("id") long id, @RequestBody UpdateUserRequest request) {
        userService.updateUser(id, request);
        return ResponseEntity.ok().build();
    }
}