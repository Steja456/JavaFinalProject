package com.example.moviecatalogue.service;

import com.example.moviecatalogue.model.Movie;
import com.example.moviecatalogue.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

@Service
public class MovieService {
    @Value("${tmdb.api.key}")
    private String apiKey;

    @Value("${tmdb.api.url}")
    private String apiUrl;

    private final RestTemplate restTemplate;
    private final MovieRepository movieRepository;

    public MovieService(RestTemplate restTemplate, MovieRepository movieRepository) {
        this.restTemplate = restTemplate;
        this.movieRepository = movieRepository;
    }

    public List<Map<String, Object>> getTrendingMovies() {
        String url = apiUrl + "/trending/movie/week?api_key=" + apiKey;
        Map<String, Object> response = restTemplate.getForObject(url, Map.class);
        return (List<Map<String, Object>>) response.get("results");
    }

    public Map<String, Object> getMovieDetails(Long id) {
        String url = apiUrl + "/movie/" + id + "?api_key=" + apiKey;
        return restTemplate.getForObject(url, Map.class);
    }

    public void addToFavorites(Movie movie) {
        movieRepository.save(movie);
    }

    public void removeFromFavorites(Long id) {
        movieRepository.deleteById(id);
    }

    public List<Movie> getFavorites() {
        return movieRepository.findAll();
    }

    public boolean isFavorite(Long id) {
        return movieRepository.existsById(id);
    }
}
