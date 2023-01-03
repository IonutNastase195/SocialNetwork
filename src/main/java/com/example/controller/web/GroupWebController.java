package com.example.controller.web;

import com.example.model.group.GroupRequest;
import com.example.model.group.GroupResponse;
import com.example.model.group.GroupUpdate;
import com.example.service.GroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/3")
public class GroupWebController {
    private final GroupService groupService;

    @GetMapping("/all")
    public String getAllGroups(Model model) {
        List<GroupResponse> groups = groupService.getAllGroups();
        model.addAttribute("groups", groups);
        return "allGroupsPage";
    }

    @GetMapping("/create")
    public String goToCreateGroupPage() {
        return "createGroupPage";
    }

    @PostMapping("/create")
    public String createGroup(@ModelAttribute(value = "groupRequest") GroupRequest groupRequest, Model model) {
        GroupResponse group = groupService.createGroup(groupRequest);
        model.addAttribute("group", group);
        return "groupCreatedPage";
    }

    @GetMapping("/update")
    public String goToUpdateGroupPage(@ModelAttribute(value = "groupId") int groupId, Model model) {
        model.addAttribute("groupId", groupId);
        return "updateGroupPage";
    }

    @PutMapping("/update")
    public String updateGroup(@ModelAttribute(value = "groupUpdate") GroupUpdate groupUpdate, Model model) {
        GroupResponse group = groupService.updateGroupById(groupUpdate.getId(), groupUpdate);
        model.addAttribute("group", group);
        return "groupUpdatedPage";
    }

    @GetMapping("/goToUpdateGroupPage")
    public String goToUpdateGroupPage(@ModelAttribute(value = "id") Integer id, Model model) {
        GroupResponse group = groupService.getGroupById(id);
        model.addAttribute("group", group);
        return "updateGroupPage";
    }

    @PostMapping("/deleteGroup")
    public String deleteGroup(@ModelAttribute(value = "id") Integer id, Model model) {
        groupService.deleteGroup(id);
        model.addAttribute("groups", groupService.getAllGroups());
        return "allGroupsPage";
    }

}
