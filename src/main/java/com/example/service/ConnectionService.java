package com.example.service;

import com.example.entity.Connection;
import com.example.repository.ConnectionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;
@Service
@Transactional
@RequiredArgsConstructor
public class ConnectionService {
    private final ConnectionRepository connectionRepository;

    public List<Connection> getAllConnections() {
        return connectionRepository.findAll();
    }

    public Optional<Connection> getConnectionById(Integer id) {
        return connectionRepository.findById(id);
    }

    public Connection addConnection(Connection connection) {
        return connectionRepository.save(connection);
    }

    public Connection updateConnection(Integer id, Connection connection) {
        return connectionRepository.save(connection);
    }

    public void deleteConnection(Integer id) {
        connectionRepository.deleteById(id);
    }
}

