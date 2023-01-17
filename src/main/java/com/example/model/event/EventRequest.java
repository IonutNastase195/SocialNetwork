package com.example.model.event;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class EventRequest {
    private Integer id;
    private String name;
    private String location;
    private LocalDateTime date;
    private List<Integer> attendees;

}

