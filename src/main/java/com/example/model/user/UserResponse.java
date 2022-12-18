package com.example.model.user;

import lombok.*;



@Data
public class UserResponse {

    private Integer userId;
    private String name;
    private String password;
    private String email;
    private Boolean active;


}
