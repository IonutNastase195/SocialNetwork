package com.example.controller.web;

import com.example.service.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class EventsWebController {

    private final EventService eventService;


    @GetMapping("/events")
    public String goToEventsPage(Model model) {
        model.addAttribute("allEvents", eventService.getAllEvents());
        return "eventsPage";
    }

    @GetMapping("/eventAttendees")
    public String eventAttendees(Model model, @RequestParam(value = "eventId") Integer id) {
        model.addAttribute("allAttendees", eventService.getAttendeesForEvent(id));
        return "eventAttendees";
    }

    @PostMapping("/joinEvent")
    public String joinEvents(Integer eventId) {
        if (eventId != null) {
            eventService.joinEvent(eventId);
        }
        return "redirect:/events";
    }

    @PostMapping("/leaveEvent")
    public String leaveEvent(Integer eventId) {
        if (eventId != null) {
            eventService.leaveEvent(eventId);
        }
        return "redirect:/events";
    }

//        @PostMapping("/addEvent")
//    public String addEvent(@ModelAttribute(value = "eventId") Integer eventId, Model model){
//        if(eventId != null){
//            User user = userDetailsSession.getUser();
//            eventService.addEvent(user.getId(), eventId);
//            userDetailsSession.setUser(userService.getUserById(user.getId()));
//        }
//        return "redirect:/eventsPage";
//    }
}
