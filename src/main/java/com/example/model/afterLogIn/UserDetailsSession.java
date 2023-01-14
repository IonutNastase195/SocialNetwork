package com.example.model.afterLogIn;



import com.example.entity.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
@SessionScope
@Component(value = "userDetailsSession")
public class UserDetailsSession {

    private User user;
    private Integer id;
    private String name;
    private String email;
    private String password;
    private List<Event> events;
    private List<Friendship> friendships;
    private List<Group> groups;
    private List<Post> posts;

    public void clear() {
        user = null;
        events = null;
        friendships = null;
        groups = null;
        posts = null;
    }

}

