package com.example.mapper;


import com.example.model.user.UserRequest;
import com.example.model.user.UserResponse;
import com.example.entity.User;
import org.mapstruct.Mapper;
import java.util.List;


@Mapper
public interface UserMapper {
    UserResponse map(User user);
    User map(UserRequest userRequest);

    List<UserResponse> map(List<User> userList);

}
