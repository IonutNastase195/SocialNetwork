package com.example.entity;

import javax.persistence.*;
import java.sql.Date;

import lombok.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name = "connections")

public class Connection {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer connectionId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "connection_id")
    private User connection;

    @Column(name = "type")
    private String type;

    @Column(name = "date_established")
    private Date dateEstablished;

    @Column(name = "notes")
    private String notes;

}
