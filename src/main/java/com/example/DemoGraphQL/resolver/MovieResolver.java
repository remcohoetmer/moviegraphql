package com.example.DemoGraphQL.resolver;

import com.coxautodev.graphql.tools.GraphQLResolver;
import com.example.DemoGraphQL.model.Movie;
import com.example.DemoGraphQL.model.Show;
import com.example.DemoGraphQL.repository.ShowRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

public class MovieResolver implements GraphQLResolver<Movie> {
  private ShowRepository showRepository;

  public MovieResolver(ShowRepository showRepository) {
    this.showRepository = showRepository;
  }

  @Transactional
  public List<Show> getShows(Movie movie) {
    return showRepository.findByMovieID(movie.getId()).collect(Collectors.toList());
  }

  @Transactional
  public List<Show> getAvailableShows(Movie movie) {
    return showRepository.findByMovieID(movie.getId()).filter(show -> show.getSeatCount() > 0).collect(Collectors.toList());
  }
}
