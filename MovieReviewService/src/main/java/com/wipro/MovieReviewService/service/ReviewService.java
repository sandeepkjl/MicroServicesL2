package com.wipro.MovieReviewService.service;


import com.wipro.MovieReviewService.entity.ReviewEntity;
import com.wipro.MovieReviewService.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ReviewService  {

    @Autowired
    private ReviewRepository reviewRepository;

    public List<ReviewEntity> getAllReviewEntity()
    {
        Iterable<ReviewEntity> entityIterable = reviewRepository.findAll();
        List<ReviewEntity> entities = new ArrayList<>();
        for(ReviewEntity entity: entityIterable )
        {
            entities.add(entity);
        }

        return  entities;
    }

    public List<ReviewEntity> getEntityByMovieName(String movieName)
    {
        List<ReviewEntity> entities = new ArrayList<>();
        Iterable<ReviewEntity> entityIterable = reviewRepository.findByMovieName(movieName);
        for(ReviewEntity entity: entityIterable )
        {
            entities.add(entity);
        }

        return  entities;
    }

    public ReviewEntity saveReviewEntityEntity(ReviewEntity entity)
    {
        return reviewRepository.save(entity);
    }

    public  String updateReviewForMovie(String userName, String reviewStar, String movieName)
    {
        reviewRepository.updateReview(userName, reviewStar,movieName);

        return "Movie Review Updated Successfully";
    }

}
