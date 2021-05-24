package com.isd.libr.service;

import com.isd.libr.repo.BookActionRepository;
import com.isd.libr.repo.UserRepository;
import com.isd.libr.web.dto.UserDto;
import com.isd.libr.web.dto.requests.UpdateUserRequest;
import com.isd.libr.web.entity.BookAction;
import com.isd.libr.web.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final BookActionRepository bookActionRepository;

    @Override
    public List<UserDto> findAll() {
        return userRepository.findAll().stream().map(UserDto::from).collect(Collectors.toList());
    }

    @Override
    public void save(User user) {
        userRepository.save(user);
    }

    @Override
    public User getById(long id) throws UserNotFoundException {
        User user = userRepository.getById(id);
        if (user == null) {
            throw new UserNotFoundException(
                    String.format("User with ID [%s] not found", id)
            );
        }
        return user;
    }

    @Override
    @Transactional
    public void deleteUserById(long id) {
        User user = userRepository.getById(id);
        bookActionRepository.deleteAllByUser(user);
        userRepository.deleteById(id);
    }

    @Override
    public void updateUser(long id, UpdateUserRequest request) {
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
