package org.acme.controller;

import java.util.List;

import org.acme.model.Review;

import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/api/reviews")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ReviewResource {

    @GET
    public List<Review> getAllReviews() {
        return Review.listAll();
    }

    @POST
    @Transactional
    public Response addReview(Review review) {
        review.persist();
        return Response.status(Response.Status.CREATED).entity(review).build();
    }

    @PUT
    @Path("/{id}")
    @Transactional
    public Response editReview(@PathParam("id") Long id, Review updatedReview) {
        Review review = Review.findById(id);
        if (review == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        review.rating = updatedReview.rating;
        review.comment = updatedReview.comment;
        return Response.ok(review).build();
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public Response deleteReview(@PathParam("id") Long id) {
        boolean deleted = Review.deleteById(id);
        if (!deleted) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.noContent().build();
    }
}
