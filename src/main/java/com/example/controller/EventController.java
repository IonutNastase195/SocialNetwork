package com.example.controller;

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
    private final EventMapper eventMapper;

    @GetMapping("/events")
    public List<EventResponse> getAllEvents() {
        return eventService.getAllEvents();
    }

    @GetMapping("/events/{id}")
    public EventResponse getEventById(@PathVariable Integer id) {
        return eventService.getEventById(id);
    }

    @PostMapping("/events")
    public EventResponse createEvent(@RequestBody EventRequest eventRequest) {
        return eventService.createEvent(eventRequest);
    }

    @PutMapping("/events/{id}")
    public void updateEventById(@PathVariable Integer id, @RequestBody EventUpdate eventUpdate) {
        eventService.updateEventById(id, eventUpdate);
    }

    @DeleteMapping("/events/{id}")
    public void deleteEvent(@PathVariable Integer id) {
        eventService.deleteEvent(id);
    }
}
