package com.example.mapper;

import com.example.entity.Post;
import com.example.entity.User;
import com.example.model.post.PostRequest;
import com.example.model.post.PostResponse;
import com.example.model.user.UserResponse;
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
public class PostMapperImpl implements PostMapper {

    @Override
    public Post map(PostRequest postRequest) {
        if ( postRequest == null ) {
            return null;
        }

        Post.PostBuilder post = Post.builder();

        post.text( postRequest.getText() );
        post.media( postRequest.getMedia() );
        post.likes( postRequest.getLikes() );
        post.comments( postRequest.getComments() );
        post.shares( postRequest.getShares() );

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
        postResponse.setMedia( post.getMedia() );
        postResponse.setLikes( post.getLikes() );
        postResponse.setComments( post.getComments() );
        postResponse.setShares( post.getShares() );
        postResponse.setCreatedAt( post.getCreatedAt() );
        postResponse.setUser( userToUserResponse( post.getUser() ) );

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

    protected UserResponse userToUserResponse(User user) {
        if ( user == null ) {
            return null;
        }

        UserResponse userResponse = new UserResponse();

        userResponse.setId( user.getId() );
        userResponse.setEmail( user.getEmail() );

        return userResponse;
    }
}
