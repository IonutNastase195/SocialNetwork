package com.example.mapper;

import com.example.entity.Post;
import com.example.model.post.PostRequest;
import com.example.model.post.PostResponse;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-01-22T15:49:19+0200",
    comments = "version: 1.5.2.Final, compiler: javac, environment: Java 17.0.3 (Eclipse Adoptium)"
)
@Component
public class PostMapperImpl implements PostMapper {

    @Override
    public Post map(PostRequest postRequest) {
        if ( postRequest == null ) {
            return null;
        }

        Post.PostBuilder post = Post.builder();

        post.text( postRequest.getText() );
        post.likes( postRequest.getLikes() );

        return post.build();
    }

    @Override
    public PostResponse map(Post post) {
        if ( post == null ) {
            return null;
        }

        PostResponse postResponse = new PostResponse();

        postResponse.setId( post.getId() );
        postResponse.setText( post.getText() );

        return postResponse;
    }

    @Override
    public List<PostResponse> map(List<Post> posts) {
        if ( posts == null ) {
            return null;
        }

        List<PostResponse> list = new ArrayList<PostResponse>( posts.size() );
        for ( Post post : posts ) {
            list.add( map( post ) );
        }

        return list;
    }
}
