package com.example.controller;

import com.example.entity.Connection;
import com.example.service.ConnectionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/connection")
public class ConnectionController {

    private final ConnectionService connectionService;

    @GetMapping("/{id}")
    public Optional<Connection> getConnectionById(@PathVariable Integer id){
        return connectionService.getConnectionById(id);
    }

    @PostMapping
    public Connection connectionCreate(@RequestBody Connection connection){
        return connectionService.addConnection(connection);
    }

    @PutMapping("/{id}")
    public Connection connectionUpdate(@PathVariable Integer id, @RequestBody Connection connection){
        return connectionService.updateConnection(id, connection);
    }

    @DeleteMapping("/{id}")
    public void deleteConnection(@RequestBody Integer id) {
        connectionService.deleteConnection(id);
    }

}
