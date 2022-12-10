package com.example.controller;

import com.example.entity.Event;
import com.example.service.implementation.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/event")
public class EventController {

    @Autowired
    private EventService eventService;

    @GetMapping("/{id}")
    public Event getEventById(@PathVariable Integer id){
        return eventService.getServiceById(id);
    }

    @PostMapping
    public Event eventCreate(@RequestBody Event event){
        return eventService.createEvent(event);
    }

    @PutMapping("/{id}")
    public Event eventUpdate(@PathVariable Integer id, @RequestBody Event event){
        return eventService.eventUpdate(id,event);
    }

    @DeleteMapping("/{id}")
    public void deleteEvent(@PathVariable Integer id){
        eventService.deleteEvent(id);
    }

}