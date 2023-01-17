package com.example.controller.web;

import com.example.model.event.EventRequest;
import com.example.model.user.UserRequest;
import com.example.service.EventService;
import com.example.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class EventsWebController {

    private final EventService eventService;

    @GetMapping("/events")
    public String goToEventsPage(Model model) {
        model.addAttribute("allEvents", eventService.getAllEvents());
        return "eventsPage";
    }

    @PostMapping("/events")
    public String allEvents(@ModelAttribute(value = "updateEvents") EventRequest request,
                             Model model) {
        EventRequest eventRequest = EventRequest.builder()
                .id(request.getId())
                .name(request.getName())
                .location(request.getLocation())
                .date(request.getDate())
                .attendees(request.getAttendees())
                .build();
        eventService.update(eventRequest);

        model.addAttribute("events", eventService.getAllEvents());
        return "eventsPage";
    }
}
