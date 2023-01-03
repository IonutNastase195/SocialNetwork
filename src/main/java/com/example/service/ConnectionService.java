package com.example.service;

import com.example.entity.Connection;
import com.example.entity.User;
import com.example.exception.BusinessException;
import com.example.mapper.ConnectionMapper;
import com.example.model.connection.ConnectionRequest;
import com.example.model.connection.ConnectionResponse;
import com.example.model.connection.ConnectionUpdate;

import com.example.repository.ConnectionRepository;
import com.example.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ConnectionService {

    private final ConnectionRepository connectionRepository;
    private final ConnectionMapper connectionMapper;
    private final UserRepository userRepository;

    public List<ConnectionResponse> getAllConnections() {
        return connectionMapper.toResponse(connectionRepository.findAll());
    }

    public ConnectionResponse getConnectionById(Integer id) {
        Connection connection = connectionRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Connection not found!")
        );
        return connectionMapper.toResponse(connection);
    }

    public ConnectionResponse createConnection(ConnectionRequest connectionRequest) {
        User user1 = userRepository.findById(connectionRequest.getUser1Id()).orElseThrow(
                () -> new BusinessException("User1 not found!")
        );
        User user2 = userRepository.findById(connectionRequest.getUser2Id()).orElseThrow(
                () -> new BusinessException("User2 not found!")
        );
        Connection connection = connectionMapper.toEntity(connectionRequest);
        connection.setUser(user1);
        connection.setUser(user2);
        connection.setStatus(connection.getStatus());
        Connection connectionSaved = connectionRepository.save(connection);
        return connectionMapper.toResponse(connectionSaved);
    }

    public ConnectionResponse updateConnectionById(Integer id, ConnectionUpdate connectionUpdate) {
        Connection connectionToUpdate = connectionRepository.findById(id).orElseThrow(
                () -> new BusinessException("The connection with the inserted id does not exist!")
        );
        connectionToUpdate.setStatus(connectionUpdate.getStatus());
        return null;
    }

    public void deleteConnection(Integer id) {
        Connection connectionToDelete = connectionRepository.findById(id).orElseThrow(() ->
                new BusinessException("The connection that you want to delete does not exist!"));
        connectionRepository.deleteById(connectionToDelete.getId());
    }
}

