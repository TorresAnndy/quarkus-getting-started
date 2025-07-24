package org.acme.controller;

import java.util.List;

import org.acme.model.Movie;

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

@Path("/api/movies")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class MovieResource {
    @GET
    public List<Movie> getAll() {
        return Movie.listAll();
    }

    @GET
    @Path("/{id}")
    public Movie getById(@PathParam("id") Long id) {
        return Movie.findById(id);
    }

    @POST
    @Transactional
    public Movie create(Movie movie) {
        movie.persist();
        return movie;
    }

    @PUT
    @Path("/{id}")
    @Transactional
    public Movie update(@PathParam("id") Long id, Movie updatedMovie) {
        Movie movie = Movie.findById(id);
        if (movie == null) {
            throw new NotFoundException();
        }
        movie.title = updatedMovie.title;
        return movie;
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public void delete(@PathParam("id") Long id) {
        Movie movie = Movie.findById(id);
        if (movie != null) {
            movie.delete();
        } else {
            throw new NotFoundException();
        }
    }
}
