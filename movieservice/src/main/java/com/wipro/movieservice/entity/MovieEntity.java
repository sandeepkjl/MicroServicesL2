package com.wipro.movieservice.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="MovieRecord")
public class MovieEntity {

    @Id
    @GeneratedValue
    private Long movieId;
    private String movieName;
    private String actorName;

    public MovieEntity() {
        super();
    }

    public MovieEntity(String movieName, String actorName) {
        this.movieName = movieName;
        this.actorName = actorName;
    }

    public Long getMovieId() {
        return movieId;
    }

    public void setMovieId(Long movieId) {
        this.movieId = movieId;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public String getActorName() {
        return actorName;
    }

    public void setActorName(String actorName) {
        this.actorName = actorName;
    }

    @Override
    public String toString() {
        return "MovieEntity{" +
                "movieId='" + movieId + '\'' +
                ", movieName='" + movieName + '\'' +
                ", actorName='" + actorName + '\'' +
                '}';
    }
}

