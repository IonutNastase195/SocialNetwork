package com.example.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name = "friendships")
public class Friendship {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
//    @JoinColumn(name = "user1_id", referencedColumnName = "id")
    @JoinColumn(name = "user1_id")
    private User user1;
    @ManyToOne
//    @JoinColumn(name = "user2_id", referencedColumnName = "id")
    @JoinColumn(name = "user2_id")
    private User user2;

}