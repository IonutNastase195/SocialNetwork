package com.example.model.user;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class UserRequest {
    private Integer id;

    @NotBlank(message = "Invalid name")
    private String name;

    @NotBlank(message = "Invalid email")
    private String email;

    @NotBlank(message = "Invalid password")
    private String password;

}
