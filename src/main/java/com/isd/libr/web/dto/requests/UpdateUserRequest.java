package com.isd.libr.web.dto.requests;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class UpdateUserRequest {
    private String email;
    private Integer age;
    private String firstName;
    private String lastName;
    private String phone;
    private String role;

    @JsonCreator
    public UpdateUserRequest(@JsonProperty String email,
                             @JsonProperty Integer age,
                             @JsonProperty String firstName,
                             @JsonProperty String lastName,
                             @JsonProperty String phone,
                             @JsonProperty String role) {
        this.email = email;
        this.age = age;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.role = role;
    }
}
