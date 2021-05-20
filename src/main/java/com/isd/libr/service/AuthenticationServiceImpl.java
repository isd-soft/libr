package com.isd.libr.service;


import com.isd.libr.repo.PersonRepository;
import com.isd.libr.web.dto.requests.RegisterRequest;
import com.isd.libr.web.entity.Person;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class AuthenticationServiceImpl implements UserDetailsService, AuthenticationService {
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
    public void updatePassword(long id, String hashedNewPassword) throws SamePasswordException {
        Person person = personRepository.getById(id);
        if (person.getPassword().equals(hashedNewPassword)) {
            throw new SamePasswordException("New and old passwords must be different");
        }
        person.setPassword(hashedNewPassword);
        personRepository.save(person);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return personRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException(String.format("User with email [%s] not found", email)));
    }
}
