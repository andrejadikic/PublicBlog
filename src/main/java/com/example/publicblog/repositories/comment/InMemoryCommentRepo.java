package com.example.publicblog.repositories.comment;

import com.example.publicblog.model.Comment;
import com.example.publicblog.repositories.post.InMemoryPostRepo;

import java.util.ArrayList;
import java.util.List;

public class InMemoryCommentRepo implements CommentRepo{
    @Override
    public synchronized Comment addComment(Long postId, Comment comment) {
        comment.setId((long) InMemoryPostRepo.getPosts().get(Math.toIntExact(postId)).getComments().size());
        InMemoryPostRepo.getPosts().get(Math.toIntExact(postId)).getComments().add(Math.toIntExact(comment.getId()),comment);
        return comment;
    }

    @Override
    public List<Comment> allComments(Long postId) {
        return new ArrayList<>(InMemoryPostRepo.getPosts().get(Math.toIntExact(postId)).getComments());
    }

    @Override
    public synchronized void deleteComment(Long postId, Long commentId) {
        InMemoryPostRepo.getPosts().get(Math.toIntExact(postId)).getComments().remove(Math.toIntExact(commentId));
        resetIndexes(Math.toIntExact(postId));
    }

    private void resetIndexes(int postId) {
        List<Comment> comments = InMemoryPostRepo.getPosts().get(Math.toIntExact(postId)).getComments();
        for(int i=0;i< comments.size();i++){
            comments.get(i).setId((long) i);
        }
    }
}
