package com.example.DemoGraphQL;

import com.example.DemoGraphQL.exception.GraphQLErrorAdapter;
import com.example.DemoGraphQL.model.Movie;
import com.example.DemoGraphQL.model.Show;
import com.example.DemoGraphQL.repository.MovieRepository;
import com.example.DemoGraphQL.repository.ShowRepository;
import com.example.DemoGraphQL.resolver.ShowResolver;
import com.example.DemoGraphQL.resolver.Mutation;
import com.example.DemoGraphQL.resolver.Query;
import graphql.ExceptionWhileDataFetching;
import graphql.GraphQLError;
import graphql.servlet.GraphQLErrorHandler;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@SpringBootApplication
public class DemoGraphQlApplication {

  public static void main(String[] args) {
    SpringApplication.run(DemoGraphQlApplication.class, args);
  }

  @Bean
  public GraphQLErrorHandler errorHandler() {
    return new GraphQLErrorHandler() {
      @Override
      public List<GraphQLError> processErrors(List<GraphQLError> errors) {
        List<GraphQLError> clientErrors = errors.stream()
          .filter(this::isClientError)
          .collect(Collectors.toList());

        List<GraphQLError> serverErrors = errors.stream()
          .filter(e -> !isClientError(e))
          .map(GraphQLErrorAdapter::new)
          .collect(Collectors.toList());

        List<GraphQLError> e = new ArrayList<>();
        e.addAll(clientErrors);
        e.addAll(serverErrors);
        return e;
      }

      protected boolean isClientError(GraphQLError error) {
        return !(error instanceof ExceptionWhileDataFetching || error instanceof Throwable);
      }
    };
  }

  @Bean
  public ShowResolver authorResolver(MovieRepository movieRepository) {
    return new ShowResolver(movieRepository);
  }

  @Bean
  public Query query(MovieRepository movieRepository, ShowRepository showRepository) {
    return new Query(movieRepository, showRepository);
  }

  @Bean
  public Mutation mutation(MovieRepository movieRepository, ShowRepository showRepository) {
    return new Mutation(movieRepository, showRepository);
  }

  @Bean
  public CommandLineRunner demo(MovieRepository movieRepository, ShowRepository showRepository) {
    return (args) -> {
      Movie movie = new Movie("Herbert", "Schildt");
      movieRepository.save(movie);

      showRepository.save(new Show("Java: A Beginner's Guide, Sixth Edition", "0071809252", 728, movie));
    };
  }
}
