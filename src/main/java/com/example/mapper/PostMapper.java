package com.example.mapper;


import com.example.entity.Post;
import com.example.model.post.PostRequest;
import com.example.model.post.PostResponse;
import org.mapstruct.Mapper;
import java.util.List;

@Mapper
public interface PostMapper {
    Post map(PostRequest postRequest);

    PostResponse map(Post post);

    List<PostResponse> map(List<Post> postList);
}
