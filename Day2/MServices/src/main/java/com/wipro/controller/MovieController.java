package com.wipro.controller;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.wipro.model.Movie;
import com.wipro.exception.MovieNotFoundException;
import com.wipro.service.MovieService;
import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/movies")
public class MovieController {
    @Autowired
    MovieService service;

    @PostMapping("/add")
    public Movie addMovie(@RequestBody Movie movie) {
        return service.save(movie);
    }

    @PostMapping("/addMultiple")
    public List<Movie> addMultipleMovies(@RequestBody List<Movie> movies) {
        return service.saveAll(movies);
    }

    @GetMapping("/{id}")
    public Optional<Movie> getById(@PathVariable int id) {
        return service.getById(id);
    }

    @PutMapping("/update/{id}")
    public Movie updateMovie(@PathVariable int id, @RequestBody Movie movie) {
        return service.updateMovie(id, movie);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteMovie(@PathVariable int id) {
        service.deleteById(id);
    }

    @GetMapping
    public List<Movie> getAllMovies() {
        return service.findAll();
    }

    @RequestMapping(method = RequestMethod.OPTIONS)
    public void handleOptions(HttpServletResponse response) {
        response.setHeader("Allow", "GET, POST, PUT, DELETE, PATCH, OPTIONS");
        response.setStatus(HttpServletResponse.SC_OK);
    }
}
