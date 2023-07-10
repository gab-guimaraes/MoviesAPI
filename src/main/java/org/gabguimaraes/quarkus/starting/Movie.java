package org.gabguimaraes.quarkus.starting;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Movie {
    private int id;
    private String title;
    private int year;
    private String genre;
}
