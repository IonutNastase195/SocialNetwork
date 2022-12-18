package com.example.service;

import com.example.entity.Group;
import com.example.repository.GroupRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;


@Service
@Transactional
@RequiredArgsConstructor
public class GroupService {
    private final GroupRepository groupRepository;

    public List<Group> getAllGroups() {
        return groupRepository.findAll();
    }

    public Optional<Group> getGroupById(Integer id) {
        return groupRepository.findById(id);
    }

    public Group addGroup(Group group) {
        return groupRepository.save(group);
    }

    public Group updateGroup(Integer id, Group group) {
        return groupRepository.save(group);
    }

    public void delete(Integer id) {
        groupRepository.deleteById(id);
    }
}
