package com.example.repository;

import com.example.entity.Event;
import com.example.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface EventRepository extends JpaRepository<Event, Integer> {
    List<Event> findByAttendeesContaining(User currentUser);
}
