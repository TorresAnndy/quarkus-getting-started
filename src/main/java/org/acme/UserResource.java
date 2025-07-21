package org.acme;


import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.Produces; 

@Path("/users")
public class UserResource {
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello(){
        return "Hola desde quarkus get rest";
    }
}
