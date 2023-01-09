package com.example.model.user;

import lombok.*;



@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserRequestToLogIn {
    private String email;
    private String password;
    private String name;
}