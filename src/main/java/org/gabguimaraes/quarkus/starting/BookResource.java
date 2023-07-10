package org.gabguimaraes.quarkus.starting;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import java.util.List;
import java.util.Optional;

@Path("/api/movies")
public class BookResource {

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public List<Movie> getAllMovies() {
        return List.of(
                new Movie(1, "The Godfather", 1972, "Crime, Drama"),
                new Movie(2, "The Shawshank Redemption", 1994, "Drama"),
                new Movie(3, "Schindler's List", 1993, "Biography, Drama, History"),
                new Movie(4, "Twilight", 2008, "Drama, Fantasy, Romance"),
                new Movie(5, "Harry Potter", 2001, "Adventure, Family, Fantasy")
        );
    }

    @GET
    @Path("/count")
    @Produces(MediaType.TEXT_PLAIN)
    public int countAllMovies() {
        return getAllMovies().size();
    }

    @GET
    @Path("/{id}")
    public Optional<Movie> countAllMovies(@PathParam("id") int id) {
        return getAllMovies().stream().filter(movie -> movie.getId() == id).findFirst();
    }
}