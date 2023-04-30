package com.example.publicblog.resources;


import com.example.publicblog.model.Post;
import com.example.publicblog.services.PostService;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/posts")
public class PostResource {
    @Inject
    PostService postService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response all(){
        return Response.ok(postService.allPosts()).build();
    }
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Post find(@PathParam("id") Long id){
        return postService.findPost(id);
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Post create(@Valid Post post){
        return postService.addPost(post);
    }

    @DELETE
    @Path("/{id}")
    public void delete(@PathParam("id") Long id){
         postService.deletePost(id);
    }


}
