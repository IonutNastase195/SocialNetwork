package com.example.mapper;

import com.example.entity.Friendship;
import com.example.model.friendship.FriendshipRequest;
import com.example.model.friendship.FriendshipResponse;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface FriendshipMapper {
    Friendship toEntity(FriendshipRequest friendshipRequest);
    FriendshipResponse toResponse(Friendship friendship);
    List<FriendshipResponse> toResponse(List<Friendship> friendships);
}
