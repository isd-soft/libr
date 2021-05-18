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
    private String email;
    private String fullName;
    private String phone;

    public static PersonDto from(Person person) {
        PersonDto result = new PersonDto();
        result.setEmail(person.getEmail());
        result.setFullName(person.getFirstName() + person.getLastName());
        result.setPhone(person.getPhone());
        return result;
    }
}
