package com.example.model.user;

import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserUpdate {
    private String name;
    private String email;
    private String password;
}
