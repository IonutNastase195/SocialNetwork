package com.example.model.group;

import lombok.*;

import java.util.List;
@Data
public class GroupUpdate {
    private String name;
    private List<Long> members;
}
