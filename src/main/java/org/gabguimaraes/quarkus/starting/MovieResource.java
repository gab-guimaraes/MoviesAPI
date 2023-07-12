package org.gabguimaraes.quarkus.starting;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.jboss.logging.Logger;

import java.util.List;
import java.util.Optional;


@Path("/api/movies")
public class MovieResource {

    @Inject
    MovieRepository movieRepository;

    @Inject
    Logger logger;

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public List<Movie> getAllMovies() {
        logger.info("return all movies");
        return movieRepository.getAllMovies();
    }

    @GET
    @Path("/count")
    @Produces(MediaType.TEXT_PLAIN)
    public int countAllMovies() {
        logger.info("return count of all movies");
        return getAllMovies().size();
    }

//    @GET
//    @Path("/{id}")
//    public Optional<Movie> getMovie(@PathParam("id") int id) {
//        logger.info("return movie with id " + id);
//        return movieRepository.getMovie(id);
//    }

    @POST
    @Path("/create")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Optional<Movie> insertMovie(Movie movie) {
        logger.info("insert movie " + movie);
        return movieRepository.insertMovie(movie);
    }
}
