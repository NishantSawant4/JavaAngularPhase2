package com.wipro.service;

import java.util.List;
import java.util.Optional;
import com.wipro.model.Movie;

public interface MovieService {
    Movie save(Movie movie);
    Optional<Movie> getById(int id);
    Movie updateMovie(int id, Movie movie);
    void deleteById(int id);
    List<Movie> findAll();
    List<Movie> saveAll(List<Movie> movies);
}
