package com.example.publicblog;

import com.example.publicblog.repositories.comment.CommentRepo;
import com.example.publicblog.repositories.comment.InMemoryCommentRepo;
import com.example.publicblog.repositories.comment.MySqlCommentRepository;
import com.example.publicblog.repositories.post.InMemoryPostRepo;
import com.example.publicblog.repositories.post.MySqlPostRepository;
import com.example.publicblog.repositories.post.PostRepo;
import com.example.publicblog.repositories.user.InMemoryUserRepository;
import com.example.publicblog.repositories.user.UserRepository;
import com.example.publicblog.services.CommentService;
import com.example.publicblog.services.PostService;
import com.example.publicblog.services.UserService;
import org.glassfish.jersey.internal.inject.AbstractBinder;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.ServerProperties;

import javax.inject.Singleton;
import javax.ws.rs.ApplicationPath;

@ApplicationPath("/api")
public class HelloApplication extends ResourceConfig {
    public HelloApplication() {
        property(ServerProperties.BV_SEND_ERROR_IN_RESPONSE,true);
        AbstractBinder binder = new AbstractBinder() {
            @Override
            protected void configure() {
//                this.bind(InMemoryCommentRepo.class).to(CommentRepo.class).in(Singleton.class);
//                this.bind(InMemoryPostRepo.class).to(PostRepo.class).in(Singleton.class);

                this.bind(MySqlPostRepository.class).to(PostRepo.class).in(Singleton.class);
                this.bind(MySqlCommentRepository.class).to(CommentRepo.class).in(Singleton.class);
                this.bind(InMemoryUserRepository.class).to(UserRepository.class).in(Singleton.class);

                this.bindAsContract(CommentService.class);
                this.bindAsContract(PostService.class);
                this.bindAsContract(UserService.class);
            }
        };
        register(binder);
        packages("com.example.publicblog");
    }
}