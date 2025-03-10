package com.wipro.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.wipro.repo.*;
import com.wipro.model.*;
import com.wipro.exception.MovieNotFoundException;

@Service
public class MovieServiceImpl implements MovieService {
    @Autowired
    MovieRepository dao;

    @Override
    public Movie save(Movie movie) {
        return dao.save(movie);
    }

    @Override
    public Optional<Movie> getById(int id) {
        Movie movie = dao.findById(id)
                         .orElseThrow(() -> new MovieNotFoundException("Movie with id " + id + " not found"));
        return Optional.of(movie);
    }

    @Override
    public Movie updateMovie(int id, Movie movie) {
        Movie existingMovie = dao.findById(id)
                                 .orElseThrow(() -> new MovieNotFoundException("Movie not found with id: " + id));

        existingMovie.setTitle(movie.getTitle());
        existingMovie.setGenre(movie.getGenre());
        existingMovie.setDuration(movie.getDuration());
        existingMovie.setRating(movie.getRating());
        
        return dao.save(existingMovie);
    }

    @Override
    public void deleteById(int id) {
        Movie movie = dao.findById(id)
                         .orElseThrow(() -> new MovieNotFoundException("Movie with id " + id + " not found"));
        dao.delete(movie);
    }

    @Override
    public List<Movie> findAll() {
        return dao.findAll();
    }

    @Override
    public List<Movie> saveAll(List<Movie> movies) {
        return dao.saveAll(movies);
    }
}
