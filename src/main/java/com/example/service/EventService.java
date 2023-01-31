package com.example.service;

import com.example.entity.Event;
import com.example.entity.User;
import com.example.mapper.UserMapper;
import com.example.model.event.EventResponse;
import com.example.model.afterLogIn.UserDetailsSession;
import com.example.model.user.UserResponse;
import com.example.repository.EventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
@Transactional
@RequiredArgsConstructor
public class EventService {

    private final EventRepository eventRepository;
    private final UserDetailsSession userDetailsSession;

    private final UserMapper userMapper;

    private final UserService userService;

    public List<EventResponse> getAllEvents() {
        List<Event> allEvents = eventRepository.findAll();
        User currentUser = userService.getUserById(userDetailsSession.getId());
        Set<Event> joinedEvents = currentUser.getEvents();
        List<EventResponse> response = new ArrayList<>();
        for (Event event : allEvents) {
            boolean isJoined = false;
            if (joinedEvents.contains(event)) {
                isJoined = true;
            }
            EventResponse eventResponse = EventResponse.builder()
                    .id(event.getId())
                    .name(event.getName())
                    .location(event.getLocation())
                    .date(event.getDate())
                    .attendees(userMapper.map(event.getAttendees()))
                    .isJoined(isJoined)
                    .build();
            response.add(eventResponse);

        }

        return response;
    }

    public Set<UserResponse> getAttendeesForEvent(Integer id){
        Event currentEvent = getEventById(id);
        Set<User> eventAttendees = currentEvent.getAttendees();

        return userMapper.map(eventAttendees);
    }

    public Event getEventById(Integer id) {
        return eventRepository.findById(id).orElseThrow(() -> new RuntimeException("Event not found!"));
    }

    public void joinEvent(Integer eventId) {
        User currentUser = userService.getUserById(userDetailsSession.getId());
        Event event = getEventById(eventId);
        event.getAttendees().add(currentUser);

        eventRepository.save(event);
    }

    public void leaveEvent(Integer eventId) {
        User currentUser = userService.getUserById(userDetailsSession.getId());
        Event event = getEventById(eventId);
        event.getAttendees().remove(currentUser);

        eventRepository.save(event);
    }
}



