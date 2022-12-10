package com.example.mapper;

import com.example.entity.User;
import com.example.model.user.UserRequest;
import org.mapstruct.Mapper;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan
@Mapper(componentModel = "spring")
public interface UserMapper {

    User map(UserRequest userRequest);
}
