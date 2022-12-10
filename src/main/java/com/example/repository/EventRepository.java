package com.example.repository;

import com.example.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EventRepository extends JpaRepository<Event, Integer> {
    @Query("select t from Event where t.event = :event")
    List<Event> findByEvent(@Param("event") String event);
}
