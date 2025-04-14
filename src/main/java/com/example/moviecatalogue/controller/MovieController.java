package com.example.moviecatalogue.controller;

import com.example.moviecatalogue.model.Movie;
import com.example.moviecatalogue.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Map;

@Controller
public class MovieController {
    private final MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("movies", movieService.getTrendingMovies());
        return "index";
    }

    @GetMapping("/movie/{id}")
    public String details(@PathVariable Long id, Model model) {
        Map<String, Object> movie = movieService.getMovieDetails(id);
        boolean isFavorite = movieService.isFavorite(id);
        model.addAttribute("movie", movie);
        model.addAttribute("isFavorite", isFavorite);
        return "detail";
    }

    @PostMapping("/favorite")
    public String addToFavorites(@ModelAttribute Movie movie) {
        movieService.addToFavorites(movie);
        return "redirect:/favorites";
    }

    @GetMapping("/favorites")
    public String viewFavorites(Model model) {
        model.addAttribute("favorites", movieService.getFavorites());
        return "favorites";
    }

    @PostMapping("/favorite/remove/{id}")
    public String removeFavorite(@PathVariable Long id) {
        movieService.removeFromFavorites(id);
        return "redirect:/favorites";
    }
}
