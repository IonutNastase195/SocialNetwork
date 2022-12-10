package com.example.controller;

import com.example.entity.Group;
import com.example.service.implementation.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/group")
public class GroupController {

    @Autowired
    private GroupService groupService;

    @GetMapping("/{id}")
    public Group getGroupById(@PathVariable Integer id) {
    return groupService.getPostById(id);
    }

    @PostMapping
    public Group groupCreate(@RequestBody Group group){
        return groupService.createGroup(group);
    }

    @PutMapping("/{id}")
    public Group groupUpdate(@PathVariable Integer id, @RequestBody Group group){
        return groupService.groupUpdate(id, group);
    }

    @DeleteMapping("/{id}")
    public void deleteGroup(@PathVariable Integer id){
        groupService.deleteGroup(id);
    }


}
