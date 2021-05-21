package com.isd.libr.service;


import com.isd.libr.repo.UserRepository;
import com.isd.libr.web.dto.requests.RegisterRequest;
import com.isd.libr.web.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class AuthenticationServiceImpl implements UserDetailsService, AuthenticationService {
    private final UserRepository userRepository;

    public User create(RegisterRequest request, String password) {
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
    public void updatePassword(long id, String hashedNewPassword) throws SamePasswordException {
        User user = userRepository.getById(id);
        if (user.getPassword().equals(hashedNewPassword)) {
            throw new SamePasswordException("New and old passwords must be different");
        }
        user.setPassword(hashedNewPassword);
        userRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException(String.format("User with email [%s] not found", email)));
    }
}
