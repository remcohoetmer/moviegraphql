package com.example.DemoGraphQL.resolver;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.example.DemoGraphQL.exception.ShowNotFoundException;
import com.example.DemoGraphQL.model.Movie;
import com.example.DemoGraphQL.model.Show;
import com.example.DemoGraphQL.repository.MovieRepository;
import com.example.DemoGraphQL.repository.ShowRepository;

public class Mutation implements GraphQLMutationResolver {
  private ShowRepository showRepository;
  private MovieRepository movieRepository;

  public Mutation(MovieRepository movieRepository, ShowRepository showRepository) {
    this.movieRepository = movieRepository;
    this.showRepository = showRepository;
  }

  public Movie newMovie(String title, String description) {
    Movie movie = new Movie();
    movie.setTitle(title);
    movie.setDescription(description);

    movieRepository.save(movie);

    return movie;
  }

  public Show newShow(String title, String isbn, Integer pageCount, Long movieId) {
    Show show = new Show();
    show.setMovie(new Movie(movieId));
    show.setTitle(title);
    show.setIsbn(isbn);
    show.setPageCount(pageCount != null ? pageCount : 0);

    showRepository.save(show);

    return show;
  }

  public boolean deleteShow(Long id) {
    showRepository.delete(id);
    return true;
  }

  public Show updateShowPageCount(Integer pageCount, Long id) {
    Show show = showRepository.findOne(id);
    if (show == null) {
      throw new ShowNotFoundException("The show to be updated was found", id);
    }
    show.setPageCount(pageCount);

    showRepository.save(show);

    return show;
  }
}
