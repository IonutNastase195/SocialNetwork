package com.example.model.user;
import lombok.*;



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



