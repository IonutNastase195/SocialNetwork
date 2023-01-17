package com.example.model.user;

import lombok.*;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {
    private Integer id;
    private String name;
    private String email;
    private String password;
    private boolean isFriend;

}
