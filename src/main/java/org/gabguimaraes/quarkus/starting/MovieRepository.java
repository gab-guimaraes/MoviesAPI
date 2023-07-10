package org.gabguimaraes.quarkus.starting;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import software.amazon.awssdk.services.dynamodb.DynamoDbBaseClientBuilder;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.AttributeValue;

import java.util.*;

@ApplicationScoped
public class MovieRepository {

    @Inject
    DynamoDbClient dynamoDbBaseClient;

    public List<Movie> getAllMovies() {
        return List.of(
                new Movie(1, "The Godfather", 1972, "Crime, Drama"),
                new Movie(2, "The Shawshank Redemption", 1994, "Drama"),
                new Movie(3, "Schindler's List", 1993, "Biography, Drama, History"),
                new Movie(4, "Twilight", 2008, "Drama, Fantasy, Romance"),
                new Movie(5, "Harry Potter", 2001, "Adventure, Family, Fantasy")
        );
    }

    public Optional<Movie> insertMovie(Movie movie) {
        Map<String, AttributeValue> itemValues = new HashMap<>();
        itemValues.put("id", AttributeValue.builder().s(UUID.randomUUID().toString()).build());
        itemValues.put("title", AttributeValue.builder().s(movie.getTitle()).build());
        itemValues.put("year", AttributeValue.builder().s(String.valueOf(movie.getYear())).build());
        itemValues.put("genre", AttributeValue.builder().s(movie.getGenre()).build());
        dynamoDbBaseClient.putItem(builder -> builder.tableName("movies").item(itemValues));
        return Optional.of(movie);
    }

    public int countAllMovies() {
        return getAllMovies().size();
    }

    public Optional<Movie> countAllMovies(int id) {
        return getAllMovies().stream().filter(movie -> movie.getId() == id).findFirst();
    }

    public Optional<Movie> getMovie(int id) {
        return getAllMovies().stream().filter(movie -> movie.getId() == id).findFirst();
    }
}
