package com.example.service;

import com.example.entity.Event;
import com.example.entity.User;
import com.example.exception.BusinessException;
import com.example.mapper.EventMapper;
import com.example.mapper.UserMapper;
import com.example.model.event.EventRequest;
import com.example.model.event.EventResponse;
import com.example.model.event.EventUpdate;
import com.example.model.afterLogIn.UserDetailsSession;
import com.example.model.user.UserResponse;
import com.example.repository.EventRepository;
import com.example.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class EventService {

    private final EventRepository eventRepository;
    private final EventMapper eventMapper;
    private final UserRepository userRepository;
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

    public void addEvent(Integer eventId, Integer newEventId) {
        Optional<Event> newEvent = eventRepository.findById(newEventId);
        if (newEvent.isPresent()) {
            User user = userRepository.findById(eventId).get();
            Event event = newEvent.get();
            user.getEvents().add(event);
            userRepository.save(user);
        }
    }

    public Event getEventById(Integer id) {
        return eventRepository.findById(id).orElseThrow(() -> new RuntimeException("Event not found!"));
    }

//    public EventResponse createEvent(EventRequest eventRequest) {
//        Event event = eventMapper.map(eventRequest);
//        event.setAttendees(getUsersByIds(eventRequest.getAttendees()));
//        Event eventSaved = eventRepository.save(event);
//        return eventMapper.toResponse(eventSaved);
//    }

//    public EventResponse updateEventById(Integer id, @NotNull EventUpdate eventUpdate) {
//        Event eventToUpdate = eventRepository.findById(id).orElseThrow(() -> new BusinessException("The event with the inserted id does not exist!"));
//        eventToUpdate.setName(eventUpdate.getName());
//        eventToUpdate.setLocation(eventUpdate.getLocation());
//        eventToUpdate.setDate(eventUpdate.getDate());
//        eventToUpdate.setAttendees(getUsersByIds(eventUpdate.getAttendees()));
//        return null;
//    }

    public void deleteEvent(Integer id) {
        Event eventToDelete = eventRepository.findById(id).orElseThrow(() -> new BusinessException("The event that you want to delete does not exist!"));
        eventRepository.deleteById(eventToDelete.getId());
    }

    private List<User> getUsersByIds(List<Integer> userIds) {
        return userIds.stream().map(userId -> userRepository.findById(userId).orElseThrow(() -> new BusinessException("User not found!"))).collect(Collectors.toList());
    }

    public List<Event> getCurrentUserEvents() {
        User currentUser = userDetailsSession.getUser();
        return eventRepository.findByAttendeesContaining(currentUser);
    }

//    public void update(EventRequest eventRequest) {
//        Event eventToUpdate = eventRepository.findById(eventRequest.getId()).orElseThrow(() -> new BusinessException(String.format("The event with id: %s not exist", eventRequest.getId())));
//        eventToUpdate.setId(eventRequest.getId());
//        eventToUpdate.setName(eventRequest.getName());
//        eventToUpdate.setLocation(eventRequest.getLocation());
//        eventToUpdate.setAttendees(userRepository.findAllById(eventRequest.getAttendees()));
//
//    }

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



