package com.example.model.group;

import com.example.model.user.UserResponse;
import lombok.*;

import java.util.List;

@Data
public class GroupResponse {
    private Long id;
    private String name;
    private List<UserResponse> members;
}
