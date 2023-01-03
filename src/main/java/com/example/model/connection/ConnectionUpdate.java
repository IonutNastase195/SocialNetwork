package com.example.model.connection;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ConnectionUpdate {
    private Integer id;
    private String status;
}
