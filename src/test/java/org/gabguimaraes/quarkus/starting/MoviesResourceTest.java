package org.gabguimaraes.quarkus.starting;

import io.quarkus.test.junit.QuarkusTest;
import jakarta.ws.rs.core.HttpHeaders;
import jakarta.ws.rs.core.MediaType;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
public class MoviesResourceTest {

    @Test
    public void shouldGetAllMovies() {
        given()
                .header(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON)
                .when()
                .get("/api/movies")
                .then()
                .statusCode(200)
                .body("size()", is(5));
    }

    @Test
    public void shouldReturnAMovie() {
        given()
                .header(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON)
                .when()
                .get("/api/movies/1")
                .then()
                .statusCode(200)
                .body("title", is("The Godfather"))
                .body("year", is(1972))
                .body("genre", is("Crime, Drama"));
    }

}