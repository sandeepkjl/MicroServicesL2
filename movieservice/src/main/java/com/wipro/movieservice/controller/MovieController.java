package com.wipro.movieservice.controller;

import com.wipro.movieservice.entity.MovieEntity;
import com.wipro.movieservice.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URI;
import java.util.List;

@Controller
public class MovieController {

    @Autowired
    private MovieService movieService;
    @Autowired
    private LoadBalancerClient loadBalancerClient;

    @Value("${pivotal.reviewservicename.name}")
    private String serviceName;

    @RequestMapping(value = "/findAllMovie", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public List<MovieEntity> getAllMovies() {
        return movieService.findAllMovie();
    }

    @RequestMapping(value = "/findMovieByName", method = RequestMethod.GET)
    @ResponseBody
    public String getMovieByName(@ModelAttribute("movieName") String movieName) {
        MovieEntity movieEntity = movieService.findMovieByMovieName(movieName);
        if (movieEntity == null) {
            return "Movie is not in the list";
        } else {
            return "<html><body bgcolor='coral'>Movie Detalis :" + "<br>" + " MovieName: " + movieEntity.getMovieName() + " MovieActor: " +
                    movieEntity.getActorName() + "<br>" + "<h2>Streaming....</h2>" +
                    "</body></html>";
        }
    }

    @RequestMapping(value = "/addMovie", method = RequestMethod.POST)
    @ResponseBody
    public String createMovie(@ModelAttribute MovieEntity movieEntity) {
        MovieEntity movie = movieService.saveMovie(movieEntity);

        return "<html><body bgcolor='coral'>Movie Added Succesfully" + "<br>" + "<a href='http://localhost:8082/MovieIndex.html'>MovieHomePage</a>" + "</body></html>";
    }

    @RequestMapping(value = "/updateMovie")
    @ResponseBody
    public String updateMovieDetails(@ModelAttribute("newMovieName") String newMovieName,
                                     @ModelAttribute("newActorName") String newActorName, @ModelAttribute("oldMovieName") String oldMovieName) {

        return movieService.udpateExistingMovie(newMovieName, newActorName, oldMovieName);
    }


    @RequestMapping(value = "/deleteMovie")
    @ResponseBody
    public String removeMovie(@ModelAttribute MovieEntity movieEntity) {
        movieService.deleteMovie(movieEntity);

        return "<html><body bgcolor='coral'>Movie deleted successfully !!!" + "<a href='http://localhost:8082/MovieIndex.html'>MovieHomePage</a>" + "</body></html>";
    }

    @RequestMapping(value = "/writeReview")
    @ResponseBody
    public String createReview(HttpServletRequest request, HttpServletResponse reponse) {
        //using ribbon for load balancing
        ServiceInstance instance = loadBalancerClient.choose(serviceName);
        URI uri = URI.create(String.format("http://%s:%s",
                instance.getHost(), instance.getPort()));


        System.out.println("Register-Service.loginUser .URI=========" + uri);
        String url = uri.toString() + "/ReviewIndex.html";
        System.out.println("====================================");
        System.out.println("Register-Service.loginUser .URI=========" + uri);
        try {

            reponse.sendRedirect(url);
        } catch (Exception e) {
            System.out.println("Error in dispacthing");
        }

        return "Error while creating review";

    }


}
