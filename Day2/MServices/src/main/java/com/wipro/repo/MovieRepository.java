package com.wipro.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import com.wipro.model.*;

public interface MovieRepository extends JpaRepository<Movie, Integer> {

}