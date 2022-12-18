package com.example.controller;

import com.example.entity.Group;
import com.example.service.GroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/group")
public class GroupController {

    private final GroupService groupService;

    @GetMapping("/{id}")
    public Optional<Group> getGroupById(@PathVariable Integer id) {
        return groupService.getGroupById(id);
    }

    @PostMapping
    public Group groupCreate(@RequestBody Group group) {
        return groupService.addGroup(group);
    }

    @PutMapping("/{id}")
    public Group groupUpdate(@PathVariable Integer id, @RequestBody Group group) {
        return groupService.updateGroup(id, group);
    }

    @DeleteMapping("/{id}")
    public void deleteGroup(@PathVariable Integer id) {
        groupService.delete(id);
    }


}
