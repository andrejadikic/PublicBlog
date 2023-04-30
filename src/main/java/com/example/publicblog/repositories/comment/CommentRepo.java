package com.example.publicblog.repositories.comment;

import com.example.publicblog.model.Comment;

import java.util.List;

public interface CommentRepo {
    Comment addComment(Long postId,Comment comment);
    List<Comment> allComments(Long postId);
    void deleteComment(Long postId,Long commentId);
}
