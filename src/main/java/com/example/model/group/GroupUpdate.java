package com.example.model.group;

import lombok.*;

import javax.validation.constraints.NotBlank;
import java.util.List;
@Data
@AllArgsConstructor
public class GroupUpdate {
    @NotBlank
    private Integer id;
    private String name;
    private List<Integer> members;
}
