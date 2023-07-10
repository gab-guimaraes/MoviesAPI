package org.gabguimaraes.quarkus.starting;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

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

    @GET
    @Path("/{id}")
    public Optional<Movie> getMovie(@PathParam("id") int id) {
        logger.info("return movie with id " + id);
        return movieRepository.getMovie(id);
    }
}
