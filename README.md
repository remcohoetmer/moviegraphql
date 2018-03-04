# graphql-java-spring-boot-example
Sample app for my tutorial [Building a GraphQL Server with Spring Boot](https://www.pluralsight.com/guides/java-and-j2ee/building-a-graphql-server-with-spring-boot). 

You'll need [Java 9](http://www.oracle.com/technetwork/java/javase/downloads/jdk9-downloads-3848520.html).

Clone this repo and execute `mvnw spring-boot:run`. Or inside an IDE, execute the class `com.example.DemoGraphQL.DemoGraphQlApplication`.

You can go to [http://localhost:8080/h2-console/login.jsp](http://localhost:8080/h2-console/login.jsp) and enter the following information:
- JDBC URL: jdbc:h2:mem:testdb
- User Name: sa
- Password: <blank>

To check the databas or to [http://localhost:8080/graphiql](http://localhost:8080/graphiql) to start executing queries. For example:
```

query FindAllMovies {
  findAllMovies {
    id
    title
    description
  }
}

query FindMoviesById($id: Long) {
  findMovie(id: $id) {
    title
    description
  }
}

query FindAllShows {
  findAllShows {
    ...ShowFragment
  }
}

fragment ShowFragment on Show {
  id
  start
  hall
  seatCount
  movie {
    title
    description
  }
}

mutation AddMovie {
  newMovie(
    title: "De Wilde Stad",
    description: "Voor een groot aantal wilde dieren, bomen en planten zijn onze straten, tunnels, waterwegen en gebouwen van baksteen net zo geschikt en aantrekkelijke als een oerbos of nieuwe wildernis. De stad verdringt of vervangt niet de natuur, het ís natuur. ")
  {
    title
    description
    id
  }
}
```


# License
MIT
