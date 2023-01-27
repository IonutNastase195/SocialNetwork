package com.example.controller.rest;

import com.example.mapper.GroupMapper;
import com.example.model.group.GroupRequest;
import com.example.model.group.GroupResponse;
import com.example.model.group.GroupUpdate;
import com.example.service.GroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;



@RequestMapping("/groups")
@RestController
@RequiredArgsConstructor
public class GroupController {

    private final GroupService groupService;

    @GetMapping("/groups")
    public List<GroupResponse> getAllGroups() {
        return groupService.getAllGroups();
    }

    @GetMapping("/groups/{id}")
    public GroupResponse getGroupById(@PathVariable Integer id) {
        return groupService.getGroupById(id);
    }

    @PostMapping("/groups")
    public GroupResponse createGroup(@RequestBody GroupRequest groupRequest) {
        return groupService.createGroup(groupRequest);
    }

    @PutMapping("/groups/{id}")
    public void updateGroupById(@PathVariable Integer id, @RequestBody GroupUpdate groupUpdate) {
        groupService.updateGroupById(id, groupUpdate);
    }

    @DeleteMapping("/groups/{id}")
    public void deleteGroup(@PathVariable Integer id) {
        groupService.deleteGroup(id);
    }
}
