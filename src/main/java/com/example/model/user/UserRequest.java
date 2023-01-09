package com.example.model.user;

import lombok.*;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserRequest {
    private Integer id;
    private String name;
    private String email;
    private String password;

}



