package com.example.model.event;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class EventRequest {
    private String name;
    private String location;
    private LocalDateTime date;
    private List<Long> attendees;
}

