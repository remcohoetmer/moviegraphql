package com.example.DemoGraphQL.model;

import javax.persistence.*;

@Entity
public class Show {
  @Id
  @Column(name = "show_id", nullable = false)
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @Column(name = "show_hall", nullable = false)
  private String hall;

  @Column(name = "show_start", nullable = false)
  private String start;

  @Column(name = "show_seats", nullable = false)
  private int seatCount;

  @ManyToOne
  @JoinColumn(name = "movie_id", nullable = false, updatable = false)
  private Movie movie;

  public Show() {
  }

  public Show(String hall, String start, int seatCount, Movie movie) {
    this.hall = hall;
    this.start = start;
    this.seatCount = seatCount;
    this.movie = movie;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getHall() {
    return hall;
  }

  public void setHall(String hall) {
    this.hall = hall;
  }

  public String getStart() {
    return start;
  }

  public void setStart(String start) {
    this.start = start;
  }

  public int getSeatCount() {
    return seatCount;
  }

  public void setSeatCount(int seatCount) {
    this.seatCount = seatCount;
  }

  public Movie getMovie() {
    return movie;
  }

  public void setMovie(Movie movie) {
    this.movie = movie;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    Show show = (Show) o;

    return id.equals(show.id);
  }

  @Override
  public int hashCode() {
    return id.hashCode();
  }

  @Override
  public String toString() {
    return "Show{" +
      "id=" + id +
      ", hall='" + hall + '\'' +
      ", start='" + start + '\'' +
      ", seatCount=" + seatCount +
      ", movie=" + movie +
      '}';
  }
}
