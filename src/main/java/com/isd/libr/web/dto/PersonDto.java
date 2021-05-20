package com.isd.libr.web.dto;

import com.isd.libr.web.entity.Person;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PersonDto {
    private long id; //
    private String email;
    private String firstName;
    private String lastName;
    private String phone;
    private int age;
    private String role;

    public static PersonDto from(Person person) {
        PersonDto result = new PersonDto();
        result.setId(person.getId());
        result.setEmail(person.getEmail());
        result.setFirstName(person.getFirstName());
        result.setLastName(person.getLastName());
        result.setPhone(person.getPhone());
        result.setAge(person.getAge());
        result.setRole(person.getRole());
        return result;
    }
}
