package com.example.DemoGraphQL;

import com.example.DemoGraphQL.exception.GraphQLErrorAdapter;
import com.example.DemoGraphQL.model.Movie;
import com.example.DemoGraphQL.model.Show;
import com.example.DemoGraphQL.repository.MovieRepository;
import com.example.DemoGraphQL.repository.ShowRepository;
import com.example.DemoGraphQL.resolver.MovieResolver;
import com.example.DemoGraphQL.resolver.Mutation;
import com.example.DemoGraphQL.resolver.Query;
import com.example.DemoGraphQL.resolver.ShowResolver;
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
  public ShowResolver showResolver(MovieRepository movieRepository) {
    return new ShowResolver(movieRepository);
  }

  @Bean
  public MovieResolver movieResolver(ShowRepository showRepository) {
    return new MovieResolver(showRepository);
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
      Movie bond = new Movie("A View to a Kill", "Agent 007 (Roger Moore) moet het opnemen tegen twee van de dodelijkste schurken: de superinteligente Max Zorin (Christopher Walken), product van een genetisch Nazi-experiment en zijn handlangerster May-Day, heerlijk boosaardig gespeeld door Grace Jones. ");
      movieRepository.save(bond);

      Movie k3 = new Movie("K3 en het ijsprinsesje", "K3 wordt uitgenodigd in een sprookjesland om daar te gaan zingen voor een prinses.");
      movieRepository.save(k3);

      Movie tarzan = new Movie("Tarzan", "De film vertelt het verhaal van John Clayton. Hij wordt geboren in de jungle aan de westkust van Afrika. Zijn ouders, John en Alice Clayton, zijn kort voor zijn geboorte achtergelaten in de jungle door een groep muiters die hun schip hadden overgenomen.");
      movieRepository.save(tarzan);

      showRepository.save(new Show("Main Hall", "Today 20:00", 100, bond.getId()));
      showRepository.save(new Show("Main Hall", "Today 21:00", 100, bond.getId()));
      showRepository.save(new Show("Main Hall", "Today 22:00", 0, bond.getId()));
      showRepository.save(new Show("Main Hall", "Today 14:00", 50, k3.getId()));
      showRepository.save(new Show("Main Hall", "Today 16:00", 50, tarzan.getId()));
    };
  }
}
