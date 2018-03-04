package com.example.DemoGraphQL.model;

import javax.persistence.*;

@Entity
public class Show {
  @Id
  @Column(name = "book_id", nullable = false)
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @Column(name = "book_title", nullable = false)
  private String title;

  @Column(name = "book_isbn", nullable = false)
  private String isbn;

  @Column(name = "book_pageCount", nullable = false)
  private int pageCount;

  @ManyToOne
  @JoinColumn(name = "author_id",
    nullable = false, updatable = false)
  private Movie movie;

  public Show() {
  }

  public Show(String title, String isbn, int pageCount, Movie movie) {
    this.title = title;
    this.isbn = isbn;
    this.pageCount = pageCount;
    this.movie = movie;
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

  public String getIsbn() {
    return isbn;
  }

  public void setIsbn(String isbn) {
    this.isbn = isbn;
  }

  public int getPageCount() {
    return pageCount;
  }

  public void setPageCount(int pageCount) {
    this.pageCount = pageCount;
  }

  public Movie getMovie() {
    return movie;
  }

  public void setMovie(Movie movie) {
    this.movie = movie;
  }

  public String getRemco() {
    return "remco";
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
      ", title='" + title + '\'' +
      ", isbn='" + isbn + '\'' +
      ", pageCount=" + pageCount +
      ", movie=" + movie +
      '}';
  }
}
