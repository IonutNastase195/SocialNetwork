package com.example.mapper;

import com.example.entity.Connection;
import com.example.model.connection.ConnectionRequest;
import com.example.model.connection.ConnectionResponse;
import com.example.model.connection.ConnectionUpdate;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-12-22T21:08:02+0200",
    comments = "version: 1.5.2.Final, compiler: javac, environment: Java 17.0.3 (Eclipse Adoptium)"
)
@Component
public class ConnectionMapperImpl implements ConnectionMapper {

    @Override
    public Connection toEntity(ConnectionRequest request) {
        if ( request == null ) {
            return null;
        }

        Connection.ConnectionBuilder connection = Connection.builder();

        connection.status( request.getStatus() );

        return connection.build();
    }

    @Override
    public ConnectionResponse toResponse(Connection entity) {
        if ( entity == null ) {
            return null;
        }

        ConnectionResponse.ConnectionResponseBuilder connectionResponse = ConnectionResponse.builder();

        connectionResponse.id( entity.getId() );
        connectionResponse.status( entity.getStatus() );

        return connectionResponse.build();
    }

    @Override
    public ConnectionUpdate toUpdate(Connection entity) {
        if ( entity == null ) {
            return null;
        }

        ConnectionUpdate.ConnectionUpdateBuilder connectionUpdate = ConnectionUpdate.builder();

        connectionUpdate.status( entity.getStatus() );

        return connectionUpdate.build();
    }

    @Override
    public List<ConnectionResponse> toResponse(List<Connection> all) {
        if ( all == null ) {
            return null;
        }

        List<ConnectionResponse> list = new ArrayList<ConnectionResponse>( all.size() );
        for ( Connection connection : all ) {
            list.add( toResponse( connection ) );
        }

        return list;
    }
}
