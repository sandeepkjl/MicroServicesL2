package com.wipro.MovieReviewService.controller;


import com.wipro.MovieReviewService.entity.ReviewEntity;
import com.wipro.MovieReviewService.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    @RequestMapping(value = "/getAllReview", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public List<ReviewEntity> getAllReviews() {
        return reviewService.getAllReviewEntity();
    }

    @RequestMapping(value = "/getMovieReview")
    @ResponseBody
    public List<ReviewEntity> getReviewByMovieName(@ModelAttribute("movieName") String movieName) {
        System.out.println(movieName);
        return reviewService.getEntityByMovieName(movieName);
    }


    @RequestMapping(value = "/addReview")
    @ResponseBody
    public ReviewEntity addReview(@ModelAttribute("userName") String userName, @ModelAttribute("movieName") String movieName, @ModelAttribute("reviewStar") String reviewStar) {

        ReviewEntity reviewEntity = new ReviewEntity(userName, movieName, reviewStar);

        return reviewService.saveReviewEntityEntity(reviewEntity);

    }


    @RequestMapping(value = "/updateReview")
    @ResponseBody
    public String updateReview(@ModelAttribute("userName") String userName, @ModelAttribute("movieName") String movieName, @ModelAttribute("reviewStar") String reviewStar) {

        return reviewService.updateReviewForMovie(userName,reviewStar, movieName);

    }


}
