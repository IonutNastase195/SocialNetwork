package com.example.mapper;

import com.example.entity.Friendship;
import com.example.entity.User;
import com.example.model.friendship.FriendshipRequest;
import com.example.model.friendship.FriendshipResponse;
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
public class FriendshipMapperImpl implements FriendshipMapper {

    @Override
    public Friendship toEntity(FriendshipRequest friendshipRequest) {
        if ( friendshipRequest == null ) {
            return null;
        }

        Friendship.FriendshipBuilder friendship = Friendship.builder();

        return friendship.build();
    }

    @Override
    public FriendshipResponse toResponse(Friendship friendship) {
        if ( friendship == null ) {
            return null;
        }

        FriendshipResponse friendshipResponse = new FriendshipResponse();

        friendshipResponse.setId( friendship.getId() );
        friendshipResponse.setUser1( userToUserResponse( friendship.getUser1() ) );
        friendshipResponse.setUser2( userToUserResponse( friendship.getUser2() ) );

        return friendshipResponse;
    }

    @Override
    public List<FriendshipResponse> toResponse(List<Friendship> friendships) {
        if ( friendships == null ) {
            return null;
        }

        List<FriendshipResponse> list = new ArrayList<FriendshipResponse>( friendships.size() );
        for ( Friendship friendship : friendships ) {
            list.add( toResponse( friendship ) );
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
}
