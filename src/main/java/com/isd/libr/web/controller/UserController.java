
package com.isd.libr.web.controller;

import com.isd.libr.service.SamePasswordException;
import com.isd.libr.service.UserService;
import com.isd.libr.web.dto.UserDto;
import com.isd.libr.web.dto.requests.RegisterRequest;
import com.isd.libr.web.dto.requests.UpdatePasswordRequest;
import com.isd.libr.web.dto.requests.UpdateUserRequest;
import com.isd.libr.web.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable("id") Long id) {
        Optional<User> user = userService.findById(id);
        UserDto userDto = UserDto.from(user.get());
        return ResponseEntity.ok(userDto);
    }

    @GetMapping()
    public List<UserDto> listUser() {
        return userService.findAll();
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

    @PutMapping("/password/{id}")
    public ResponseEntity<?> updatePassword(@PathVariable("id") long id, @RequestBody UpdatePasswordRequest updatePasswordRequest) throws SamePasswordException {
        userService.updatePassword(id, updatePasswordRequest);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/register")
    public ResponseEntity<?> createUser(@RequestBody RegisterRequest request) {
        User user = userService.create(request);
        return ResponseEntity.ok(user);
    }
}

