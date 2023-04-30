package com.example.publicblog.services;

import com.example.publicblog.model.Post;
import com.example.publicblog.repositories.post.PostRepo;

import javax.inject.Inject;
import java.util.List;

public class PostService {
    @Inject
    PostRepo postRepo;

    public Post addPost(Post post){
        return postRepo.addPost(post);
    }
    public List<Post> allPosts(){
        return postRepo.allPosts();
    }
    public Post findPost(Long id){
        return postRepo.findPost(id);
    }
    public void deletePost(Long id){
        postRepo.deletePost(id);
    }
}
