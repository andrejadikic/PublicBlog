package com.example.publicblog.services;

import com.example.publicblog.model.Comment;
import com.example.publicblog.repositories.comment.CommentRepo;

import javax.inject.Inject;
import java.util.List;

public class CommentService {
    @Inject
    private CommentRepo commentRepo;
    public Comment addComment(Long postId, Comment comment){
        return commentRepo.addComment(postId,comment);
    }
    public List<Comment> allComments(Long postId){
        return commentRepo.allComments(postId);
    }
}
