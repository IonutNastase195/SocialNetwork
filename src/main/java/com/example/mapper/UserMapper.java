package com.example.mapper;


import com.example.model.user.UpdateProfileRequest;
import com.example.model.user.UserRequest;
import com.example.model.user.UserResponse;
import com.example.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;


@Mapper
public interface UserMapper {

    User toEntity(UserRequest userRequest);
    UserResponse toResponse(User user);
    List<UserResponse> toResponse(List<User> users);


    User updateEntity(User user);

}
