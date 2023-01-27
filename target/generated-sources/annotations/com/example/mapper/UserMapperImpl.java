package com.example.mapper;

import com.example.entity.User;
import com.example.model.user.UserRequest;
import com.example.model.user.UserResponse;
import java.util.LinkedHashSet;
import java.util.Set;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-01-22T15:52:17+0200",
    comments = "version: 1.5.2.Final, compiler: javac, environment: Java 17.0.3 (Eclipse Adoptium)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public User map(UserRequest userRequest) {
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
    public UserResponse map(User user) {
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
    public Set<UserResponse> map(Set<User> allUsers) {
        if ( allUsers == null ) {
            return null;
        }

        Set<UserResponse> set = new LinkedHashSet<UserResponse>( Math.max( (int) ( allUsers.size() / .75f ) + 1, 16 ) );
        for ( User user : allUsers ) {
            set.add( map( user ) );
        }

        return set;
    }
}
