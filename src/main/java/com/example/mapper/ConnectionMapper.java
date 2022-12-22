package com.example.mapper;

import com.example.entity.Connection;
import com.example.model.connection.ConnectionRequest;
import com.example.model.connection.ConnectionResponse;
import com.example.model.connection.ConnectionUpdate;
import org.mapstruct.Mapper;

import java.util.List;


@Mapper
public interface ConnectionMapper {
    Connection toEntity(ConnectionRequest request);
    ConnectionResponse toResponse(Connection entity);
    ConnectionUpdate toUpdate(Connection entity);

    List<ConnectionResponse> toResponse(List<Connection> all);
}
