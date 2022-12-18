package com.example.mapper;

import com.example.entity.User;
import com.example.model.user.UserRequest;
import com.example.model.user.UserResponse;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-12-18T20:07:52+0200",
    comments = "version: 1.5.2.Final, compiler: javac, environment: Java 17.0.3 (Eclipse Adoptium)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public UserResponse map(User user) {
        if ( user == null ) {
            return null;
        }

        UserResponse userResponse = new UserResponse();

        userResponse.setUserId( user.getUserId() );
        userResponse.setName( user.getName() );
        userResponse.setPassword( user.getPassword() );
        userResponse.setEmail( user.getEmail() );
        userResponse.setActive( user.getActive() );

        return userResponse;
    }

    @Override
    public User map(UserRequest userRequest) {
        if ( userRequest == null ) {
            return null;
        }

        User.UserBuilder user = User.builder();

        user.userId( userRequest.getUserId() );
        user.name( userRequest.getName() );
        user.password( userRequest.getPassword() );
        user.email( userRequest.getEmail() );
        user.active( userRequest.getActive() );

        return user.build();
    }

    @Override
    public List<UserResponse> map(List<User> userList) {
        if ( userList == null ) {
            return null;
        }

        List<UserResponse> list = new ArrayList<UserResponse>( userList.size() );
        for ( User user : userList ) {
            list.add( map( user ) );
        }

        return list;
    }
}
