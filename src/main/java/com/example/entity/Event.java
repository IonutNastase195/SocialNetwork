package com.example.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name = "events")

public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer eventId;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "location")
    private String location;

    @JsonFormat(pattern = "dd-MM-yyyy")
    @Column(name = "start_time")
    private Date startTime;

    @JsonFormat(pattern = "dd-MM-yyyy")
    @Column(name = "end_time")
    private Date endTime;

    @ManyToMany
    @JoinTable(
            name = "event_attendees",
            joinColumns = @JoinColumn(name = "event_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private Set<User> attendees;

}
