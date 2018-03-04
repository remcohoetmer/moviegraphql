package com.example.DemoGraphQL.model;

import javax.persistence.*;

@Entity
public class Movie {
  @Id
  @Column(name = "book_id", nullable = false)
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @Column(name = "author_first_name", nullable = false)
  private String title;

  @Column(name = "author_last_name", nullable = false)
  private String description;

  public Movie() {
  }

  public Movie(Long id) {
    this.id = id;
  }

  public Movie(String firstName, String description) {
    this.title = firstName;
    this.description = description;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    Movie movie = (Movie) o;

    return id.equals(movie.id);
  }

  @Override
  public int hashCode() {
    return id.hashCode();
  }

  @Override
  public String toString() {
    return "Movie{" +
      "id=" + id +
      ", title='" + title + '\'' +
      ", description='" + description + '\'' +
      '}';
  }
}
