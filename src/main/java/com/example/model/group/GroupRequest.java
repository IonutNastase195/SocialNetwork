package com.example.model.group;

import lombok.*;

import java.util.List;
@Data
public class GroupRequest {
    private String name;
    private List<Integer> membersId;

}


