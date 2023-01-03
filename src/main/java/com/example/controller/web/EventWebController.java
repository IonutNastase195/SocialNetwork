package com.example.controller.web;

import com.example.model.event.EventRequest;
import com.example.model.event.EventResponse;
import com.example.model.event.EventUpdate;
import com.example.service.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequiredArgsConstructor
@RequestMapping("/5")
public class EventWebController {

    private final EventService eventService;

    @GetMapping("/all")
    public String getAllEvents(Model model) {
        model.addAttribute("events", eventService.getAllEvents());
        return "allEventsPage";
    }

    @GetMapping("/create")
    public String goToCreateEventPage() {
        return "createEventPage";
    }

    @PostMapping("/create")
    public String createEvent(@ModelAttribute(value = "eventRequest") EventRequest eventRequest, Model model) {
        EventResponse event = eventService.createEvent(eventRequest);
        model.addAttribute("event", event);
        return "eventCreatedPage";
    }

    @GetMapping("/update")
    public String goToUpdateEventPage(@ModelAttribute(value = "eventId") int eventId, Model model) {
        model.addAttribute("eventId", eventId);
        return "updateEventPage";
    }

    @PostMapping("/update")
    public String updateEvent(@ModelAttribute(value = "eventUpdate") EventUpdate eventUpdate, Model model) {
        EventResponse event = eventService.updateEventById(eventUpdate.getId(), eventUpdate);
        model.addAttribute("event", event);
        return "eventUpdatedPage";
    }

    @PostMapping("/delete")
    public String deleteEvent(@ModelAttribute(value = "eventId") int eventId, Model model) {
        eventService.deleteEvent(eventId);
        model.addAttribute("events", eventService.getAllEvents());
        return "allEventsPage";
    }
}
