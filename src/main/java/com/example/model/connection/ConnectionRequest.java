package com.example.model.connection;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ConnectionRequest {
    private Integer user1Id;
    private Integer user2Id;
    private String status;

}

