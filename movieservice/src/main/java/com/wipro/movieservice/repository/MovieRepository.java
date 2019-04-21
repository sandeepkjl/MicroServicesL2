package com.wipro.movieservice.repository;

import com.wipro.movieservice.entity.MovieEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


import java.util.Optional;

@Repository
public interface MovieRepository extends CrudRepository<MovieEntity, Long>
{
    @Override
    MovieEntity save(MovieEntity movieEntity);

    @Override
    Optional<MovieEntity> findById(Long aLong);

    MovieEntity findByMovieName(String movieName);

    @Modifying
    @Query(value = "update  Movie_Record set Movie_Name=?1, Actor_Name=?2 where Movie_Name=?3",nativeQuery = true)
    @Transactional
    public void updateMovie(String newMovieName, String newActorName, String oldMovieName );

    @Override
    Iterable<MovieEntity> findAll();

    @Override
    void delete(MovieEntity movieEntity);
}
