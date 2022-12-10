package com.example.controller;

import com.example.entity.Connection;
import com.example.service.implementation.ConnectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/connection")
public class ConnectionController {

    @Autowired
    private ConnectionService connectionService;

    @GetMapping("/{id}")
    public Connection getConnectionById(@PathVariable Integer id){
        return connectionService.findById(id);
    }

    @PostMapping
    public Connection connectionCreate(@RequestBody Connection connection){
        return connectionService.create(connection);
    }

    @PutMapping("/{id}")
    public Connection connectionUpdate(@PathVariable Integer id, @RequestBody Connection connection){
        return connectionService.update(id, connection);
    }

    @DeleteMapping("/{id}")
    public void deleteConnection(@RequestBody Integer id) {
        connectionService.delete(id);
    }

}
