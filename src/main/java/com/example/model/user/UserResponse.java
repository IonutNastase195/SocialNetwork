package com.example.model.user;

import lombok.*;

import java.time.LocalDateTime;



@Data
public class UserResponse {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private LocalDateTime createdAt;
}
