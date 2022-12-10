package com.example.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"media"})
@Table(name = "posts")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer postId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "text")
    private String text;

    @Lob
    @Column(name = "media")
    private String media;

    @Column(name = "likes")
    private Integer likes;

    @Column(name = "comments")
    private Integer comments;

    @Column(name = "shares")
    private Integer shares;
}