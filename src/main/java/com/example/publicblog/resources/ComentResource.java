package com.example.publicblog.resources;

import com.example.publicblog.model.Comment;
import com.example.publicblog.services.CommentService;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class ComentResource {
    @Inject
    CommentService commentService;

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response find(@PathParam("id") Long id){
        return Response.ok(commentService.allComments(id)).build();
    }

    @POST
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Comment create(@PathParam("id") Long id,@Valid Comment comment){
        return commentService.addComment(id,comment);
    }
}
