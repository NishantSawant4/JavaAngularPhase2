package com.microservice1.model;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.GeneratorType;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Movie {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
 
    private String name;
    private String director;
 
    @ElementCollection
    private List<String> actors = new ArrayList<>();
	
}
