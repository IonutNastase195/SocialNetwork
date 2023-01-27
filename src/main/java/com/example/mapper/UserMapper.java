package com.example.mapper;

import com.example.model.user.UserRequest;
import com.example.model.user.UserResponse;
import com.example.entity.User;
import org.mapstruct.Mapper;

import java.util.List;
import java.util.Set;


@Mapper
public interface UserMapper {
    User map(UserRequest userRequest);
    UserResponse map(User user);
    Set<UserResponse> map(Set<User> allUsers);

}
