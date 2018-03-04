package com.example.DemoGraphQL.repository;

import com.example.DemoGraphQL.model.Movie;
import org.springframework.data.repository.CrudRepository;

public interface MovieRepository extends CrudRepository<Movie, Long> {
}
