package com.isd.libr.service;


import com.isd.libr.repo.PersonRepository;
import com.isd.libr.web.dto.JwtResponse;
import com.isd.libr.web.entity.Person;
import com.isd.libr.web.requests.LoginRequest;
import com.isd.libr.web.requests.RegisterRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService implements UserDetailsService {
    private final PersonRepository personRepository;

    public Person create(RegisterRequest request, String password) {
        Person person = Person.builder()
                .email(request.getEmail())
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .age(request.getAge())
                .password(password)
                .role(request.getRole())
                .phone(request.getPhone())
                .build();
        return personRepository.save(person);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return personRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException(String.format("User with email [%s] not found", email)));
    }
}
