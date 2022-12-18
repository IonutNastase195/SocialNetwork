package com.example.entity;

import javax.persistence.*;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name = "t_connections")

public class Connection {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "connection_id")
    private User connection;

    @Column(name = "type")
    private String type;

    @JsonFormat(pattern = "dd-MM-yyyy")
    @Column(name = "date_established")
    private Date dateEstablished;

    @Column(name = "notes")
    private String notes;

}
