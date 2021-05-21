package com.isd.libr.service;

import com.isd.libr.repo.UserRepository;
import com.isd.libr.web.dto.UserDto;
import com.isd.libr.web.dto.requests.UpdatePersonRequest;
import com.isd.libr.web.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public List<UserDto> findAll() {
        return userRepository.findAll().stream().map(UserDto::from).collect(Collectors.toList());
    }

    @Override
    public void save(User user) {
        userRepository.save(user);
    }

    @Override
    public void deletePersonById(long id) {
        userRepository.deleteById(id);
    }

    @Override
    public void updateUser(long id, UpdatePersonRequest request) {
        User user = userRepository.getById(id);
        user.setEmail(request.getEmail());
        user.setAge(request.getAge());
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setPhone(request.getPhone());
        user.setRole(request.getRole());
        userRepository.save(user);
    }
}
