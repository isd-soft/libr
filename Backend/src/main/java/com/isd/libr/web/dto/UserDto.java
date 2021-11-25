package com.isd.libr.web.dto;

import com.isd.libr.web.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserDto {
    private long id; //
    private String email;
    private String firstName;
    private String lastName;
    private String phone;
    private int age;
    private String role;

    public static UserDto from(User user) {
        UserDto result = new UserDto();
        result.setId(user.getId());
        result.setEmail(user.getEmail());
        result.setFirstName(user.getFirstName());
        result.setLastName(user.getLastName());
        result.setPhone(user.getPhone());
        result.setAge(user.getAge());
        result.setRole(user.getRole());
        return result;
    }
}
