package com.example.mapper;

import com.example.entity.Event;
import com.example.entity.Friendship;
import com.example.entity.Group;
import com.example.entity.Post;
import com.example.entity.User;
import com.example.model.user.UserRequest;
import com.example.model.user.UserResponse;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-01-15T20:49:23+0200",
    comments = "version: 1.5.2.Final, compiler: javac, environment: Java 17.0.3 (Eclipse Adoptium)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public User toEntity(UserRequest userRequest) {
        if ( userRequest == null ) {
            return null;
        }

        User.UserBuilder user = User.builder();

        user.id( userRequest.getId() );
        user.name( userRequest.getName() );
        user.email( userRequest.getEmail() );
        user.password( userRequest.getPassword() );

        return user.build();
    }

    @Override
    public UserResponse toResponse(User user) {
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

    @Override
    public List<UserResponse> toResponse(List<User> users) {
        if ( users == null ) {
            return null;
        }

        List<UserResponse> list = new ArrayList<UserResponse>( users.size() );
        for ( User user : users ) {
            list.add( toResponse( user ) );
        }

        return list;
    }

    @Override
    public User updateEntity(User user) {
        if ( user == null ) {
            return null;
        }

        User.UserBuilder user1 = User.builder();

        user1.id( user.getId() );
        user1.name( user.getName() );
        user1.email( user.getEmail() );
        user1.password( user.getPassword() );
        List<Event> list = user.getEvents();
        if ( list != null ) {
            user1.events( new ArrayList<Event>( list ) );
        }
        List<Friendship> list1 = user.getFriendships();
        if ( list1 != null ) {
            user1.friendships( new ArrayList<Friendship>( list1 ) );
        }
        List<Group> list2 = user.getGroups();
        if ( list2 != null ) {
            user1.groups( new ArrayList<Group>( list2 ) );
        }
        List<Post> list3 = user.getPosts();
        if ( list3 != null ) {
            user1.posts( new ArrayList<Post>( list3 ) );
        }

        return user1.build();
    }
}
