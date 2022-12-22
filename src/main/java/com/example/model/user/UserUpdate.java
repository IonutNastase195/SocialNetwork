package com.example.model.user;

import lombok.*;

import javax.validation.constraints.NotBlank;

@Data
public class UserUpdate {
    private String name;
    private String email;
    private String password;
}
