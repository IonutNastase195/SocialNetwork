package com.example.mapper;

import com.example.entity.Event;
import com.example.entity.User;
import com.example.model.event.EventRequest;
import com.example.model.event.EventResponse;
import com.example.model.user.UserResponse;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-12-22T21:08:02+0200",
    comments = "version: 1.5.2.Final, compiler: javac, environment: Java 17.0.3 (Eclipse Adoptium)"
)
@Component
public class EventMapperImpl implements EventMapper {

    @Override
    public User map(Long value) {
        if ( value == null ) {
            return null;
        }

        User.UserBuilder user = User.builder();

        return user.build();
    }

    @Override
    public Event toEntity(EventRequest eventRequest) {
        if ( eventRequest == null ) {
            return null;
        }

        Event.EventBuilder event = Event.builder();

        event.name( eventRequest.getName() );
        event.location( eventRequest.getLocation() );
        event.date( eventRequest.getDate() );
        event.attendees( map( eventRequest.getAttendees() ) );

        return event.build();
    }

    @Override
    public EventResponse toResponse(Event event) {
        if ( event == null ) {
            return null;
        }

        EventResponse eventResponse = new EventResponse();

        eventResponse.setId( event.getId() );
        eventResponse.setName( event.getName() );
        eventResponse.setLocation( event.getLocation() );
        eventResponse.setDate( event.getDate() );
        eventResponse.setAttendees( userListToUserResponseList( event.getAttendees() ) );

        return eventResponse;
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
    public List<User> map(List<Long> value) {
        if ( value == null ) {
            return null;
        }

        List<User> list = new ArrayList<User>( value.size() );
        for ( Long long1 : value ) {
            list.add( map( long1 ) );
        }

        return list;
    }

    protected UserResponse userToUserResponse(User user) {
        if ( user == null ) {
            return null;
        }

        UserResponse userResponse = new UserResponse();

        userResponse.setId( user.getId() );
        userResponse.setEmail( user.getEmail() );

        return userResponse;
    }

    protected List<UserResponse> userListToUserResponseList(List<User> list) {
        if ( list == null ) {
            return null;
        }

        List<UserResponse> list1 = new ArrayList<UserResponse>( list.size() );
        for ( User user : list ) {
            list1.add( userToUserResponse( user ) );
        }

        return list1;
    }
}
