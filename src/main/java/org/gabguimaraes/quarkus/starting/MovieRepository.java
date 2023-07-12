package org.gabguimaraes.quarkus.starting;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.jboss.logging.Logger;
import software.amazon.awssdk.services.dynamodb.DynamoDbBaseClientBuilder;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.AttributeValue;
import software.amazon.awssdk.services.dynamodb.model.PutItemRequest;

import java.util.*;

@ApplicationScoped
public class MovieRepository {

    @Inject
    DynamoDbClient dynamoDbBaseClient;

    @Inject
    Logger logger;

    public List<Movie> getAllMovies() {
        return List.of(
                new Movie("The Godfather", 1972, "Crime, Drama"),
                new Movie("The Shawshank Redemption", 1994, "Drama"),
                new Movie("Schindler's List", 1993, "Biography, Drama, History"),
                new Movie("Twilight", 2008, "Drama, Fantasy, Romance"),
                new Movie("Harry Potter", 2001, "Adventure, Family, Fantasy")
        );
    }

    public Optional<Movie> insertMovie(Movie movie) {
        logger.info("creating global table" + movie);
        Map<String, AttributeValue> itemValues = new HashMap<>();
        itemValues.put("id", AttributeValue.builder().s(UUID.randomUUID().toString()).build());
        itemValues.put("title", AttributeValue.builder().s(movie.getTitle()).build());
        itemValues.put("year", AttributeValue.builder().s(String.valueOf(movie.getYear())).build());
        itemValues.put("genre", AttributeValue.builder().s(movie.getGenre()).build());
        logger.info("create item in DynamoDB");
        PutItemRequest.builder().tableName("movies").item(itemValues).build();
//        dynamoDbBaseClient.putItem(builder -> builder.tableName("movies").item(itemValues).build());
        return Optional.of(movie);
    }

    public int countAllMovies() {
        return getAllMovies().size();
    }


    public void getMovie(int id) {
//        return getAllMovies().stream().filter(movie -> movie.getId() == id).findFirst();
    }
}
