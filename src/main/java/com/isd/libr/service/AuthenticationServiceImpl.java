package com.isd.libr.service;


import com.isd.libr.repo.UserRepository;
import com.isd.libr.web.dto.requests.RegisterRequest;
import com.isd.libr.web.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
class AuthenticationServiceImpl implements UserDetailsService, AuthenticationService {
    private final UserRepository userRepository;

    public User create(RegisterRequest request, String password) {
        Optional<User> existingUser = userRepository.findByEmail(request.getEmail());
        if (existingUser.isPresent()) {
            throw new UserAlreadyExistsException(String.format("User with [%s] already exists! Consider logging in.", request.getEmail()));
        }
        User user = User.builder()
                .email(request.getEmail())
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .age(request.getAge())
                .password(password)
                .role(request.getRole())
                .phone(request.getPhone())
                .build();
        return userRepository.save(user);
    }

    @Override
    public void updatePassword(long id, String hashedPassword) {
        User user = userRepository.getById(id);
        user.setPassword(hashedPassword);
        userRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException(String.format("User with email [%s] not found", email)));
    }
}
