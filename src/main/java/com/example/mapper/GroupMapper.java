package com.example.mapper;

import com.example.entity.Group;
import com.example.entity.User;
import com.example.model.group.GroupRequest;
import com.example.model.group.GroupResponse;
import com.example.model.group.GroupUpdate;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;
@Mapper
public interface GroupMapper {
    Group toEntity(GroupRequest groupRequest);
    GroupResponse toResponse(Group group);
    List<GroupResponse> toResponse(List<Group> groups);
    User map(Integer value);
    List<User> map(List<Integer> value);
}