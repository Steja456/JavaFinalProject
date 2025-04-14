package com.example.moviecatalogue.model;

import jakarta.persistence.*;

@Entity
public class Movie {
    @Id
    private Long id;
    private String title;
    private String overview;
    private String posterPath;
    private String releaseDate;
    private Double rating;

    // Getters and Setters
}

