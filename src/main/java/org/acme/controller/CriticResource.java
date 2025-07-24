package org.acme.controller;

import java.util.List;

import org.acme.model.Critic;

import jakarta.transaction.Transactional;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/api/critics")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CriticResource {
    @GET
    public List<Critic> getAll() {
        return Critic.listAll();
    }

    @GET
    @Path("/{id}")
    public Critic getById(@PathParam("id") Long id) {
        return Critic.findById(id);
    }

    @POST
    @Transactional
    public Critic create(Critic critic) {
        critic.persist();
        return critic;
    }

    @PUT
    @Path("/{id}")
    @Transactional
    public Critic update(@PathParam("id") Long id, Critic updatedCritic) {
        Critic critic = Critic.findById(id);
        if (critic == null) {
            throw new NotFoundException();
        }
        critic.name = updatedCritic.name;
        return critic;
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public void delete(@PathParam("id") Long id) {
        Critic critic = Critic.findById(id);
        if (critic != null) {
            critic.delete();
        } else {
            throw new NotFoundException();
        }
    }
}
