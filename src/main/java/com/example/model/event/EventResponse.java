package com.example.model.event;

import com.example.model.user.UserResponse;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Data
public class EventResponse {
    private Long id;
    private String name;
    private String location;
    private LocalDateTime date;
    private List<UserResponse> attendees;
}
