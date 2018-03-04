package com.example.DemoGraphQL.resolver;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.example.DemoGraphQL.model.Movie;
import com.example.DemoGraphQL.model.Show;
import com.example.DemoGraphQL.repository.MovieRepository;
import com.example.DemoGraphQL.repository.ShowRepository;

public class Query implements GraphQLQueryResolver {
  private ShowRepository showRepository;
  private MovieRepository movieRepository;

  public Query(MovieRepository movieRepository, ShowRepository showRepository) {
    this.movieRepository = movieRepository;
    this.showRepository = showRepository;
  }

  public Iterable<Show> findAllShows() {
    return showRepository.findAll();
  }

  public Iterable<Movie> findAllMovies() {
    return movieRepository.findAll();
  }

  public Movie findMovie(Long id) {
    return movieRepository.findOne(id);
  }

  public long countShows() {
    return showRepository.count();
  }

  public long countMovies() {
    return movieRepository.count();
  }
}
