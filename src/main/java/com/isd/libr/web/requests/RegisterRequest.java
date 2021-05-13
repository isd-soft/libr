package com.isd.libr.web.requests;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RegisterRequest {
    private String email;
    private String firstName;
    private String lastName;
    private Integer age;
    private String passwd;
    private String role;
    private String phone;

    @JsonCreator
    public RegisterRequest(@JsonProperty String email,
                           @JsonProperty String firstName,
                           @JsonProperty String lastName,
                           @JsonProperty Integer age,
                           @JsonProperty("password") String passwd,
                           @JsonProperty String role,
                           @JsonProperty String phone) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.passwd = passwd;
        this.role = role;
        this.phone = phone;
    }

}
