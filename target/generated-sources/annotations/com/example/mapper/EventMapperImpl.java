package com.example.mapper;

import com.example.entity.Event;
import com.example.entity.User;
import com.example.model.event.EventRequest;
import com.example.model.event.EventResponse;
import com.example.model.user.UserResponse;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-01-30T20:35:59+0200",
    comments = "version: 1.5.2.Final, compiler: javac, environment: Java 17.0.3 (Eclipse Adoptium)"
)
@Component
public class EventMapperImpl implements EventMapper {

    @Override
    public User map(Integer value) {
        if ( value == null ) {
            return null;
        }

        User.UserBuilder user = User.builder();

        return user.build();
    }

    @Override
    public Event map(EventRequest eventRequest) {
        if ( eventRequest == null ) {
            return null;
        }

        Event.EventBuilder event = Event.builder();

        event.id( eventRequest.getId() );
        event.name( eventRequest.getName() );
        event.location( eventRequest.getLocation() );
        event.date( eventRequest.getDate() );
        event.attendees( integerListToUserSet( eventRequest.getAttendees() ) );

        return event.build();
    }

    @Override
    public EventResponse toResponse(Event event) {
        if ( event == null ) {
            return null;
        }

        EventResponse.EventResponseBuilder eventResponse = EventResponse.builder();

        eventResponse.id( event.getId() );
        eventResponse.name( event.getName() );
        eventResponse.location( event.getLocation() );
        eventResponse.date( event.getDate() );
        eventResponse.attendees( userSetToUserResponseSet( event.getAttendees() ) );

        return eventResponse.build();
    }

    @Override
    public List<EventResponse> toResponse(List<Event> events) {
        if ( events == null ) {
            return null;
        }

        List<EventResponse> list = new ArrayList<EventResponse>( events.size() );
        for ( Event event : events ) {
            list.add( toResponse( event ) );
        }

        return list;
    }

    @Override
    public List<User> map(List<Integer> value) {
        if ( value == null ) {
            return null;
        }

        List<User> list = new ArrayList<User>( value.size() );
        for ( Integer integer : value ) {
            list.add( map( integer ) );
        }

        return list;
    }

    protected Set<User> integerListToUserSet(List<Integer> list) {
        if ( list == null ) {
            return null;
        }

        Set<User> set = new LinkedHashSet<User>( Math.max( (int) ( list.size() / .75f ) + 1, 16 ) );
        for ( Integer integer : list ) {
            set.add( map( integer ) );
        }

        return set;
    }

    protected UserResponse userToUserResponse(User user) {
        if ( user == null ) {
            return null;
        }

        UserResponse.UserResponseBuilder userResponse = UserResponse.builder();

        userResponse.id( user.getId() );
        userResponse.name( user.getName() );
        userResponse.email( user.getEmail() );
        userResponse.password( user.getPassword() );

        return userResponse.build();
    }

    protected Set<UserResponse> userSetToUserResponseSet(Set<User> set) {
        if ( set == null ) {
            return null;
        }

        Set<UserResponse> set1 = new LinkedHashSet<UserResponse>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( User user : set ) {
            set1.add( userToUserResponse( user ) );
        }

        return set1;
    }
}
