package com.example.model.connection;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ConnectionRequest {
    private Long user1Id;
    private Long user2Id;
    private String status;

}

