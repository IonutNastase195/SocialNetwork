package com.example.model.user;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserUpdate {
    private String name;
    private String email;
    private String password;
}