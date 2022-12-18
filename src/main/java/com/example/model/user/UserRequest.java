package com.example.model.user;

import lombok.*;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserRequest {

    private Integer userId;
    @NotBlank(message = "Invalid name")
    private String name;
    @NotBlank
    private String password;
    @NotBlank
    private String email;
    private Boolean active;


}



