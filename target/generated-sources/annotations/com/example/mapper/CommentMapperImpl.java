package com.example.mapper;

import com.example.entity.Comment;
import com.example.model.comment.CommentRequest;
import com.example.model.comment.CommentResponse;
import com.example.model.comment.CommentUpdate;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-01-15T20:49:22+0200",
    comments = "version: 1.5.2.Final, compiler: javac, environment: Java 17.0.3 (Eclipse Adoptium)"
)
@Component
public class CommentMapperImpl implements CommentMapper {

    @Override
    public Comment toEntity(CommentRequest request) {
        if ( request == null ) {
            return null;
        }

        Comment.CommentBuilder comment = Comment.builder();

        return comment.build();
    }

    @Override
    public CommentResponse toResponse(Comment entity) {
        if ( entity == null ) {
            return null;
        }

        CommentResponse.CommentResponseBuilder commentResponse = CommentResponse.builder();

        commentResponse.id( entity.getId() );

        return commentResponse.build();
    }

    @Override
    public CommentUpdate toUpdate(Comment entity) {
        if ( entity == null ) {
            return null;
        }

        CommentUpdate.CommentUpdateBuilder commentUpdate = CommentUpdate.builder();

        commentUpdate.id( entity.getId() );

        return commentUpdate.build();
    }

    @Override
    public List<CommentResponse> toResponse(List<Comment> all) {
        if ( all == null ) {
            return null;
        }

        List<CommentResponse> list = new ArrayList<CommentResponse>( all.size() );
        for ( Comment comment : all ) {
            list.add( toResponse( comment ) );
        }

        return list;
    }
}
