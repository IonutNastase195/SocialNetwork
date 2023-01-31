package com.example.model.event;

import com.example.model.user.UserResponse;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EventResponse {
    private Integer id;
    private String name;
    private String location;
    private LocalDateTime date;
    private Set<UserResponse> attendees;
    private boolean isJoined;
}
