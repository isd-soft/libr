package com.isd.libr.service;

import com.isd.libr.repo.BookActionRepository;
import com.isd.libr.repo.UserRepository;
import com.isd.libr.exceptions.SamePasswordException;
import com.isd.libr.exceptions.UserAlreadyExistsException;
import com.isd.libr.exceptions.UserNotFoundException;
import com.isd.libr.service.UserService;
import com.isd.libr.web.dto.UserDto;
import com.isd.libr.web.dto.requests.RegisterRequest;
import com.isd.libr.web.dto.requests.UpdatePasswordRequest;
import com.isd.libr.web.dto.requests.UpdateUserRequest;
import com.isd.libr.web.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Implementation for {@link UserService}
 *
 * @author Grosu Kirill
 */
@Service
@RequiredArgsConstructor
class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final BookActionRepository bookActionRepository;
    private final PasswordEncoder passwordEncoder;
    private final CommentService commentService;
    private final BookReactionService bookReactionService;
    private final CommentReactionService commentReactionService;
    private final VoteService voteService;

    @Override
    public List<UserDto> findAll() {
        return userRepository.findAll().stream().map(UserDto::from).collect(Collectors.toList());
    }

    public User create(RegisterRequest request) {
        Optional<User> existingUser = userRepository.findByEmail(request.getEmail());
        if (existingUser.isPresent()) {
            throw new UserAlreadyExistsException(String.format("User with [%s] already exists! Consider logging in.", request.getEmail()));
        }
        User user = User.builder()
                .email(request.getEmail())
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .age(request.getAge())
                .password(passwordEncoder.encode(request.getPasswd()))
                .role(request.getRole())
                .phone(request.getPhone())
                .build();
        return userRepository.save(user);
    }

    @Override
    public void updatePassword(long id, UpdatePasswordRequest request) throws SamePasswordException {
        Optional<User> user = userRepository.findById(id);
        if (user.isEmpty()) {
            throw new UserNotFoundException(String.format("User with ID [%s] not found", id));
        }
        if (passwordEncoder.matches(request.getNewPassword(), user.get().getPassword())) {
            throw new SamePasswordException("New and old passwords must be different");
        }
        String hashedPassword = passwordEncoder.encode(request.getNewPassword());
        user.get().setPassword(hashedPassword);
        userRepository.save(user.get());
    }

    @Override
    public Optional<User> findById(long id) throws UserNotFoundException {
        Optional<User> user = userRepository.findById(id);
        if (user.isEmpty()) {
            throw new UserNotFoundException(
                    String.format("User with ID [%s] not found", id)
            );
        }
        return user;
    }

    @Override
    @Transactional
    public void deleteUserById(long id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isEmpty()) {
            throw new UserNotFoundException(String.format("User with ID [%s] not found", id));
        }
        bookActionRepository.deleteAllByUser(user);
        commentReactionService.deleteByUser(user);
        bookReactionService.deleteByUser(user);
        commentService.deleteByUser(user);
        voteService.deleteByUser(user);
        userRepository.deleteById(id);
    }

    @Override
    public void updateUser(long id, UpdateUserRequest request) {
        Optional<User> optionalUserByEmail = userRepository.findByEmail(request.getEmail());
        if (optionalUserByEmail.isPresent()
                && !optionalUserByEmail.get().getFirstName().equals(request.getFirstName())
                && !optionalUserByEmail.get().getLastName().equals(request.getLastName())
                && !optionalUserByEmail.get().getPhone().equals(request.getPhone())) {
            throw new UserAlreadyExistsException(String.format("[%s] already exists! Consider another one.", request.getEmail()));
        }

        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isEmpty()) {
            throw new UserNotFoundException(String.format("User with ID [%s] not found", id));
        }
        User user = optionalUser.get();
        user.setEmail(request.getEmail());
        user.setAge(request.getAge());
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setPhone(request.getPhone());
        user.setRole(request.getRole());
        userRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException(String.format("User with email [%s] not found", email)));
    }

    @Override
    public Integer countUsers() {
        return userRepository.countUsersByIdGreaterThan(0L);
    }
}
