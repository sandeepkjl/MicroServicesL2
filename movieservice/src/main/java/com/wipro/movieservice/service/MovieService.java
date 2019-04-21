package com.wipro.movieservice.service;

import com.wipro.movieservice.entity.MovieEntity;
import com.wipro.movieservice.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class MovieService {

    @Autowired
    private MovieRepository movieRepository;

    public List<MovieEntity> findAllMovie()
    {
        Iterable<MovieEntity> allMovie = movieRepository.findAll();
        List<MovieEntity>  movies= new ArrayList<MovieEntity>();
        for(MovieEntity movie : allMovie )
        {
            movies.add(movie);
        }

        return movies;
    }

    public MovieEntity findMovieByMovieName(String movieName)
    {
        return movieRepository.findByMovieName(movieName);

    }

    public String udpateExistingMovie(String newMovieName, String newActorName, String oldMovieName)
    {
        movieRepository.updateMovie(newMovieName, newActorName, oldMovieName);

        return "Movie Updated Successfully";
    }


    public MovieEntity saveMovie(MovieEntity movie)
    {
        return movieRepository.save(movie);
    }

    public MovieEntity deleteMovie(MovieEntity movie){
        movieRepository.delete(movie);
        return movie;
    }


}
