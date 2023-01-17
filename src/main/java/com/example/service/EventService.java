package com.example.service;

import com.example.entity.Event;
import com.example.entity.User;
import com.example.exception.BusinessException;
import com.example.mapper.EventMapper;
import com.example.model.event.EventRequest;
import com.example.model.event.EventResponse;
import com.example.model.event.EventUpdate;
import com.example.model.afterLogIn.UserDetailsSession;
import com.example.repository.EventRepository;
import com.example.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class EventService {

    private final EventRepository eventRepository;
    private final EventMapper eventMapper;
    private final UserRepository userRepository;
    private final UserDetailsSession userDetailsSession;

    public List<EventResponse> getAllEvents() {
        return eventMapper.toResponse(eventRepository.findAll());
    }

    public EventResponse getEventById(Integer id) {
        Event event = eventRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Event not found!")
        );
        return eventMapper.toResponse(event);
    }

    public EventResponse createEvent(EventRequest eventRequest) {
        Event event = eventMapper.map(eventRequest);
        event.setAttendees(getUsersByIds(eventRequest.getAttendees()));
        Event eventSaved = eventRepository.save(event);
        return eventMapper.toResponse(eventSaved);
    }

    public EventResponse updateEventById(Integer id, @NotNull EventUpdate eventUpdate) {
        Event eventToUpdate = eventRepository.findById(id).orElseThrow(
                () -> new BusinessException("The event with the inserted id does not exist!")
        );
        eventToUpdate.setName(eventUpdate.getName());
        eventToUpdate.setLocation(eventUpdate.getLocation());
        eventToUpdate.setDate(eventUpdate.getDate());
        eventToUpdate.setAttendees(getUsersByIds(eventUpdate.getAttendees()));
        return null;
    }

    public void deleteEvent(Integer id) {
        Event eventToDelete = eventRepository.findById(id).orElseThrow(() ->
                new BusinessException("The event that you want to delete does not exist!"));
        eventRepository.deleteById(eventToDelete.getId());
    }

    private List<User> getUsersByIds(List<Integer> userIds) {
        return userIds.stream().map(userId ->
                userRepository.findById(userId).orElseThrow(() ->
                        new BusinessException("User not found!"))
        ).collect(Collectors.toList());
    }

    public List<Event> getCurrentUserEvents() {
        User currentUser = userDetailsSession.getUser();
        return eventRepository.findByAttendeesContaining(currentUser);
    }

    public void update(EventRequest eventRequest) {
        Event eventToUpdate = eventRepository.findById(eventRequest.getId()).orElseThrow(
                () -> new BusinessException(
                        String.format("The event with id: %s not exist", eventRequest.getId())
                ));
        eventToUpdate.setId(eventRequest.getId());
        eventToUpdate.setName(eventRequest.getName());
        eventToUpdate.setLocation(eventRequest.getLocation());
        eventToUpdate.setAttendees(userRepository.findAllById(eventRequest.getAttendees()));

    }
}



