package org.acme;

import java.util.List;

import org.acme.micro.User;

import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/api")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserResource {

    @GET
    @Path("/hola")
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        return "Hola desde quarkus get rest";
    }

    @GET
    @Path("/users")
    public List<User> getAllUsers() {
        return User.listAll();
    }

    @POST
    @Path("/users")
    @Transactional
    public Response addUser(User user) {
        user.persist();
        return Response.status(Response.Status.CREATED).entity(user).build();
    }

    @PUT
    @Path("/users/{id}")
    @Transactional
    public Response editUser(@PathParam("id") Long id, User updatedUser) {
        User user = User.findById(id);
        if (user == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        user.name = updatedUser.name;
        user.email = updatedUser.email;
        user.phone = updatedUser.phone;
        return Response.ok(user).build();
    }

    @DELETE
    @Path("/users/{id}")
    @Transactional
    public Response deleteUser(@PathParam("id") Long id) {
        boolean deleted = User.deleteById(id);
        if (!deleted) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.noContent().build(); // 204 sin cuerpo
    }
}
