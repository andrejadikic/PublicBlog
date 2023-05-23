package com.example.publicblog.repositories.comment;


import com.example.publicblog.model.Comment;
import com.example.publicblog.model.Post;
import com.example.publicblog.repositories.MySqlAbstractRepository;
import com.example.publicblog.repositories.post.PostRepo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySqlCommentRepository extends MySqlAbstractRepository implements CommentRepo {
    @Override
    public Comment addComment(Long postId, Comment comment) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = this.newConnection();
            String[] generatedColumns = {"id"};

            preparedStatement = connection.prepareStatement("INSERT INTO comments (name,comment, post_id) VALUES(?, ?, ?)", generatedColumns);
            preparedStatement.setString(1, comment.getName());
            preparedStatement.setString(2, comment.getComment());
            preparedStatement.setInt(3, Math.toIntExact(postId));
            preparedStatement.executeUpdate();
            resultSet = preparedStatement.getGeneratedKeys();

            if (resultSet.next()) {
                comment.setId((long)resultSet.getInt(1));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(preparedStatement);
            this.closeResultSet(resultSet);
            this.closeConnection(connection);
        }

        return comment;
    }

    @Override
    public List<Comment> allComments(Long postId) {
        List<Comment> comments = new ArrayList<>();

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = this.newConnection();
            statement = connection.prepareStatement("select * from comments WHERE post_id = ?");
            statement.setInt(1, Math.toIntExact(postId));
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                comments.add(new Comment((long)resultSet.getInt("id"), resultSet.getString("name"),resultSet.getString("comment")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(statement);
            this.closeResultSet(resultSet);
            this.closeConnection(connection);
        }

        return comments;
    }

    @Override
    public void deleteComment(Long postId, Long commentId) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = this.newConnection();

            preparedStatement = connection.prepareStatement("DELETE FROM comments where id = ?");
            preparedStatement.setInt(1, Math.toIntExact(commentId));
            preparedStatement.executeUpdate();

            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(preparedStatement);
            this.closeConnection(connection);
        }
    }
}
