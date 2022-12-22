package com.example.model.user;

import lombok.*;

import javax.validation.constraints.NotBlank;

@Data
public class UserRequest {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
}



