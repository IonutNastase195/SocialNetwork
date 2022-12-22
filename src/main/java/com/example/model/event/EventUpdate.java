package com.example.model.event;

import lombok.*;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Data
public class EventUpdate {
    private String name;
    private String location;
    private LocalDateTime date;
    private List<Long> attendees;
}
