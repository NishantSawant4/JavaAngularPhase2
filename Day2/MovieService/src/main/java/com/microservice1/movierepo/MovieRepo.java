package com.microservice1.movierepo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.microservice1.model.Movie;

@Repository
public interface MovieRepo extends JpaRepository<Movie, Long>{

}
