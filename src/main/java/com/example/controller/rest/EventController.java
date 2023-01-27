package com.example.controller.rest;

import com.example.mapper.EventMapper;
import com.example.model.event.EventRequest;
import com.example.model.event.EventResponse;
import com.example.model.event.EventUpdate;
import com.example.service.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;



@RequestMapping("/event")
@RestController
@RequiredArgsConstructor
public class EventController {

    private final EventService eventService;





    @DeleteMapping("/events/{id}")
    public void deleteEvent(@PathVariable Integer id) {
        eventService.deleteEvent(id);
    }
}
