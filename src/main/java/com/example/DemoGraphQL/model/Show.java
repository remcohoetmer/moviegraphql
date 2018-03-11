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

  @Column(name = "movieID", nullable = false, updatable = false)
  private Long movieID;

  public Show() {
  }

  public Show(String hall, String start, int seatCount, Long movieID) {
    this.hall = hall;
    this.start = start;
    this.seatCount = seatCount;
    this.movieID = movieID;
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

  public Long getMovieID() {
    return movieID;
  }

  public void setMovieID(Long movieID) {
    this.movieID = movieID;
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
      ", movie=" + movieID +
      '}';
  }
}
