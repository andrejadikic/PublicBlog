package com.example.publicblog.repositories.post;

import com.example.publicblog.model.Post;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class InMemoryPostRepo implements PostRepo{
    private static List<Post> posts = new CopyOnWriteArrayList<>();

    public static List<Post> getPosts(){
        return posts;
    }

    @Override
    public synchronized Post addPost(Post post) {
        post.setId((long) posts.size());
        posts.add(Math.toIntExact(post.getId()),post);
        return post;
    }

    @Override
    public List<Post> allPosts() {
        return new ArrayList<>(posts);
    }

    @Override
    public Post findPost(Long id) {
        return posts.get(Math.toIntExact(id));
    }

    @Override
    public synchronized void deletePost(Long id) {
        posts.remove(id.intValue());
        resetIndexes();
    }

    private void resetIndexes() {
        for(int i=0;i< posts.size();i++){
            posts.get(i).setId((long) i);
        }
    }
}
