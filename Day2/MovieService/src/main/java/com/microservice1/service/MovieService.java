package com.microservice1.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.microservice1.model.Movie;
import com.microservice1.movierepo.MovieRepo;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class MovieService {

	@Autowired
	private MovieRepo movie;
	public Movie createMovie(Movie m) {
	    if (m == null) {
	        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid movie");
	    }
	    return movie.save(m);
	}

	public Movie readMovie(long id) {
	    return movie.findById(id)
	            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Movie Not Found"));
	}

	public Movie updateMovie(long id, Movie updatedMovie) {
	    Movie existingMovie = movie.findById(id)
	            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Movie not found"));

	    existingMovie.setName(updatedMovie.getName());
	    existingMovie.setDirector(updatedMovie.getDirector());
	    existingMovie.setActors(updatedMovie.getActors());

	    return movie.save(existingMovie);
	}

	public void deleteMovie(long id) {
	    if (movie.existsById(id)) {
	        movie.deleteById(id);
	    } else {
	        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Movie Not Found");
	    }
	}
//	public Movie createMovie(Movie m) {
//		if(m == null) {
//			throw new RuntimeException("Invalid movie");
//		}
//		return movie.save(m);
//		
//	}
//	public Movie readMovie(long id) {
//		return movie.findById(id).orElseThrow(()-> new RuntimeException("Movie Not Found"));
//		 
//	}
//	
//	public Movie updateMovie(long id, Movie updatedMovie) {
//        Movie existingMovie = movie.findById(id)
//                .orElseThrow(() -> new RuntimeException("Movie not found"));
// 
//        existingMovie.setName(updatedMovie.getName());
//        existingMovie.setDirector(updatedMovie.getDirector());
//        existingMovie.setActors(updatedMovie.getActors());
// 
//        return movie.save(existingMovie);
//    }
//	public void deleteMovie(long id) {
//		if(movie.existsById(id)) {
//			movie.deleteById(id);
//		}else {
//			throw new RuntimeException("Movie Not Found");
//		}
//	}
}
