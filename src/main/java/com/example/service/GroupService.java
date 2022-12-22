package com.example.service;

import com.example.entity.Connection;
import com.example.entity.Group;
import com.example.entity.User;
import com.example.exception.BusinessException;
import com.example.mapper.GroupMapper;
import com.example.model.group.GroupRequest;
import com.example.model.group.GroupResponse;
import com.example.model.group.GroupUpdate;
import com.example.repository.GroupRepository;
import com.example.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class GroupService {

    private final GroupRepository groupRepository;
    private final GroupMapper groupMapper;
    private final UserRepository userRepository;

    public List<GroupResponse> getAllGroups() {
        return groupMapper.toResponse(groupRepository.findAll());
    }

    public GroupResponse getGroupById(Integer id) {
        Group group = groupRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Group not found!")
        );
        return groupMapper.toResponse(group);
    }

    public GroupResponse createGroup(GroupRequest groupRequest) {
        Group group = groupMapper.toEntity(groupRequest);
        group.setMembers(getUsersByIds(groupRequest.getMembers()));
        Group groupSaved = groupRepository.save(group);
        return groupMapper.toResponse(groupSaved);
    }

    public void updateGroupById(Integer id, GroupUpdate groupUpdate) {
        Group groupToUpdate = groupRepository.findById(id).orElseThrow(
                () -> new BusinessException("The group with the inserted id does not exist!")
        );
        groupToUpdate.setName(groupUpdate.getName());
        groupToUpdate.setMembers(getUsersByIds(groupUpdate.getMembers()));
    }

    public void deleteGroup(Integer id) {
        Group groupToDelete = groupRepository.findById(id).orElseThrow(() ->
                new BusinessException("The group that you want to delete does not exist!"));
        groupRepository.deleteById(groupToDelete.getId());
    }

    private List<User> getUsersByIds(List<Integer> userIds) {
        return userIds.stream().map(userId ->
                userRepository.findById(userId).orElseThrow(() ->
                        new BusinessException("User not found!"))
        ).collect(Collectors.toList());
    }
}