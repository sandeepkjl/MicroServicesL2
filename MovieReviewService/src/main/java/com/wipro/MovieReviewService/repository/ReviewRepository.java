package com.wipro.MovieReviewService.repository;

import com.wipro.MovieReviewService.entity.ReviewEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Repository
public interface ReviewRepository extends CrudRepository<ReviewEntity, Long> {

    @Override
    ReviewEntity  save(ReviewEntity reviewEntity);

    @Override
    Iterable<ReviewEntity> findAll();

    Iterable<ReviewEntity> findByMovieName(String movieName);

    @Modifying
    @Query(value = "update Review_entity set review_star=?1 where user_name=?2 and movie_name=?2", nativeQuery = true)
    @Transactional
    public void updateReview(String reviewStar,String userName, String movieName);


}
