package com.example.DemoGraphQL.resolver;

import com.coxautodev.graphql.tools.GraphQLResolver;
import com.example.DemoGraphQL.model.Movie;
import com.example.DemoGraphQL.model.Show;
import com.example.DemoGraphQL.repository.MovieRepository;

public class ShowResolver implements GraphQLResolver<Show> {
  private MovieRepository movieRepository;

  public ShowResolver(MovieRepository movieRepository) {
    this.movieRepository = movieRepository;
  }

  public Movie getMovie(Show show) {
    return movieRepository.findOne(show.getMovie().getId());
  }

  public Integer getAvailableSeats(Show show) {
    return show.getSeatCount() / 2;
  }
}
