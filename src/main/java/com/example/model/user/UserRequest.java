package com.example.model.user;

import lombok.*;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;


@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserRequest {
    private Integer id;
    private String name;
    private String email;
    private String password;
}



