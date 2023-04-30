package com.example.publicblog.repositories.post;

import com.example.publicblog.model.Post;

import java.util.List;

public interface PostRepo {
    Post addPost(Post post);
    List<Post> allPosts();
    Post findPost(Long id);
    void deletePost(Long id);
}
