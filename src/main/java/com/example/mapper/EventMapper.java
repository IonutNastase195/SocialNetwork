package com.example.mapper;

import com.example.entity.Event;
import com.example.entity.User;
import com.example.model.event.EventRequest;
import com.example.model.event.EventResponse;
import org.mapstruct.Mapper;
import java.util.List;

@Mapper
public interface EventMapper {

    User map(Integer value);
    Event map(EventRequest eventRequest);
    EventResponse toResponse(Event event);
    List<EventResponse> toResponse(List<Event> events);

    List<User> map(List<Integer> value);

}