package com.example.entity;

import lombok.*;
import org.hibernate.annotations.Fetch;
import org.springframework.data.repository.cdi.Eager;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 30)
    private String name;

    @Column(nullable = false, unique = true, length = 45)
    private String email;

    @Column(nullable = false, length = 64)
    private String password;

    @ManyToMany
    @JoinTable(name = "attendees",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "event_id")
    )
    private List<Event> events;

    //    @OneToMany(fetch = FetchType.EAGER, mappedBy = "user2_id")
    @OneToMany
    @JoinTable(name = "friendships")
    private Set<User> friends = new HashSet<>();

    //    @OneToMany(fetch = FetchType.EAGER, mappedBy = "user2_id")
    @OneToMany
    @JoinTable(name = "friendships")
    private Set<User> befriended = new HashSet<>();

    @OneToMany
    private List<Group> groups;

    @OneToMany
    private List<Post> posts;

}
