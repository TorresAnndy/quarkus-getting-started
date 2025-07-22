package org.acme;


import java.util.List;

import org.acme.micro.User;

import jakarta.transaction.Transactional;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.Produces; 

@Path("/users")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserResource {
    @GET
    @Path("/hola")
    @Produces(MediaType.TEXT_PLAIN)
    public String hello(){
        return "Hola desde quarkus get rest";
    }

    @GET
    public List<User> getAllUsers(){
        return User.listAll();
    }

    @POST
    @Transactional
    public void addUser(User user){
        user.persist();
    }
}
