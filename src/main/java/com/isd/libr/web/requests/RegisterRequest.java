package com.isd.libr.web.requests;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class RegisterRequest {
    private String email;
    private String firstName;
    private String lastName;
    private Integer age;
    private String passwd;
    private List<String> roles;
    private String phone;

    public RegisterRequest() {
    }

    @JsonCreator
    public RegisterRequest(@JsonProperty String email,
                           @JsonProperty String firstName,
                           @JsonProperty String lastName,
                           @JsonProperty Integer age,
                           @JsonProperty("password") String passwd,
                           @JsonProperty List<String> roles,
                           @JsonProperty String phone) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.passwd = passwd;
        this.roles = roles;
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Integer getAge() {
        return age;
    }

    public String getPasswd() {
        return passwd;
    }

    public List<String> getRoles() {
        return roles;
    }

    public String getPhone() {
        return phone;
    }

}
