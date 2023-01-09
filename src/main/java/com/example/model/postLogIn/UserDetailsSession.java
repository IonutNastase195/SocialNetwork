package com.example.model.postLogIn;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;


@Data
@NoArgsConstructor
@AllArgsConstructor
@SessionScope
@Component(value = "userDetailsSession")
public class UserDetailsSession {
    private String user;
    private String password;

}

