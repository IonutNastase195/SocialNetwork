package com.example.mapper;

import com.example.entity.Group;
import com.example.entity.User;
import com.example.model.group.GroupRequest;
import com.example.model.group.GroupResponse;
import com.example.model.user.UserResponse;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-01-17T20:26:22+0200",
    comments = "version: 1.5.2.Final, compiler: javac, environment: Java 17.0.3 (Eclipse Adoptium)"
)
@Component
public class GroupMapperImpl implements GroupMapper {

    @Override
    public Group toEntity(GroupRequest groupRequest) {
        if ( groupRequest == null ) {
            return null;
        }

        Group.GroupBuilder group = Group.builder();

        group.name( groupRequest.getName() );

        return group.build();
    }

    @Override
    public GroupResponse toResponse(Group group) {
        if ( group == null ) {
            return null;
        }

        GroupResponse groupResponse = new GroupResponse();

        groupResponse.setId( group.getId() );
        groupResponse.setName( group.getName() );
        groupResponse.setMembers( userListToUserResponseList( group.getMembers() ) );

        return groupResponse;
    }

    @Override
    public List<GroupResponse> toResponse(List<Group> groups) {
        if ( groups == null ) {
            return null;
        }

        List<GroupResponse> list = new ArrayList<GroupResponse>( groups.size() );
        for ( Group group : groups ) {
            list.add( toResponse( group ) );
        }

        return list;
    }

    @Override
    public User map(Integer value) {
        if ( value == null ) {
            return null;
        }

        User.UserBuilder user = User.builder();

        return user.build();
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
