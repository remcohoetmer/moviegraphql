package com.example.DemoGraphQL.repository;

import com.example.DemoGraphQL.model.Show;
import org.springframework.data.repository.CrudRepository;

import java.util.stream.Stream;

public interface ShowRepository extends CrudRepository<Show, Long> {
  Stream<Show> findByMovieID(Long id);
}
