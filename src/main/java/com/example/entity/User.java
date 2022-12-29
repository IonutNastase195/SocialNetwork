package com.example.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;


@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    @NotBlank
    private String name;
    @Column
    @NotBlank
    private String email;
    private String password;
    @OneToMany
    private List<Connection> connections;
    @ManyToMany
    @JoinTable(name = "attendees",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "event_id")
    )
    private List<Event> events;
    @OneToMany
    private List<Friendship> friendships;
    @OneToMany
    private List<Group> groups;
    @OneToMany
    private List<Post> posts;

}
