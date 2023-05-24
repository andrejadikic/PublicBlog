package com.example.publicblog.repositories.post;


import com.example.publicblog.model.Comment;
import com.example.publicblog.model.Post;
import com.example.publicblog.repositories.MySqlAbstractRepository;
import com.example.publicblog.repositories.comment.CommentRepo;
import com.example.publicblog.repositories.comment.MySqlCommentRepository;
import com.example.publicblog.services.CommentService;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySqlPostRepository extends MySqlAbstractRepository implements PostRepo {

    @Override
    public Post addPost(Post post) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = this.newConnection();
            String[] generatedColumns = {"id"};

            preparedStatement = connection.prepareStatement("INSERT INTO posts (author,title, content) VALUES(?, ?, ?)", generatedColumns);
            preparedStatement.setString(1, post.getAuthor());
            preparedStatement.setString(2, post.getTitle());
            preparedStatement.setString(3, post.getContent());
            preparedStatement.executeUpdate();
            resultSet = preparedStatement.getGeneratedKeys();

            if (resultSet.next()) {
                post.setId((long)resultSet.getInt(1));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(preparedStatement);
            this.closeResultSet(resultSet);
            this.closeConnection(connection);
        }

        return post;
    }

    @Override
    public List<Post> allPosts() {
        List<Post> posts = new ArrayList<>();

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            connection = this.newConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery("select * from posts");
            while (resultSet.next()) {
                Post post = new Post((long)resultSet.getInt("id"), resultSet.getString("author"),resultSet.getString("title"), resultSet.getString("content"));
                st = connection.prepareStatement("select * from comments WHERE post_id = ?");
                st.setInt(1, Math.toIntExact(post.getId()));
                rs = st.executeQuery();
                while(rs.next()){
                    post.getComments().add(new Comment((long)rs.getInt("id"), rs.getString("name"),rs.getString("comment")));
                }
                posts.add(post);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(statement);
            this.closeResultSet(resultSet);
            this.closeConnection(connection);
        }

        return posts;
    }

    @Override
    public Post findPost(Long id) {
        Post post = null;

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            connection = this.newConnection();

            preparedStatement = connection.prepareStatement("SELECT * FROM posts where id = ?");
            preparedStatement.setInt(1, Math.toIntExact(id));
            resultSet = preparedStatement.executeQuery();

            if(resultSet.next()) {
                int subjectId = resultSet.getInt("id");
                String author = resultSet.getString("author");
                String title = resultSet.getString("title");
                String content = resultSet.getString("content");
                post = new Post((long) subjectId,author, title, content);

                st = connection.prepareStatement("select * from comments WHERE post_id = ?");
                st.setInt(1, Math.toIntExact(post.getId()));
                rs = st.executeQuery();
                while(rs.next()){
                    post.getComments().add(new Comment((long)rs.getInt("id"), rs.getString("name"),rs.getString("comment")));
                }
            }

            resultSet.close();
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(preparedStatement);
            this.closeResultSet(resultSet);
            this.closeConnection(connection);
        }

        return post;
    }

    @Override
    public void deletePost(Long id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = this.newConnection();

            preparedStatement = connection.prepareStatement("DELETE FROM posts where id = ?");
            preparedStatement.setInt(1, Math.toIntExact(id));
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
