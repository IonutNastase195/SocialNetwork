package com.example.model.user;

import lombok.*;

import java.time.LocalDateTime;



@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserResponse {
    private String name;
    private String email;
    private String password;
}
