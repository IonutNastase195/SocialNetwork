package com.example.controller;

import com.example.entity.Connection;
import com.example.mapper.ConnectionMapper;
import com.example.model.connection.ConnectionRequest;
import com.example.model.connection.ConnectionResponse;
import com.example.model.connection.ConnectionUpdate;
import com.example.service.ConnectionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RequestMapping("/connection")
@RestController
@RequiredArgsConstructor
public class ConnectionController {

    private final ConnectionService connectionService;
    private final ConnectionMapper connectionMapper;

    @GetMapping("/connections")
    public List<ConnectionResponse> getAllConnections() {
        return connectionService.getAllConnections();
    }

    @GetMapping("/connections/{id}")
    public ConnectionResponse getConnectionById(@PathVariable Integer id) {
        return connectionService.getConnectionById(id);
    }

    @PostMapping("/connections")
    public ConnectionResponse createConnection(@RequestBody ConnectionRequest connectionRequest) {
        return connectionService.createConnection(connectionRequest);
    }

    @PutMapping("/connections/{id}")
    public void updateConnectionById(@PathVariable Integer id, @RequestBody ConnectionUpdate connectionUpdate) {
        connectionService.updateConnectionById(id, connectionUpdate);
    }

    @DeleteMapping("/connections/{id}")
    public void deleteConnection(@PathVariable Integer id) {
        connectionService.deleteConnection(id);
    }
}
