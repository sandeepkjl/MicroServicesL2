package com.wipro.movieuser.controller;


import com.wipro.movieuser.entity.User;
import com.wipro.movieuser.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private LoadBalancerClient loadBalancerClient;

    @Autowired
    private RestTemplate restTemplate;

    @Value("${pivotal.movieservicename.name}")
    private String movieservicename;

    @Value("${pivotal.reviewservicename.name}")
    private String reviewservice;


    @RequestMapping(value = "/signUp", method = RequestMethod.POST)
    public String userSignUp(@ModelAttribute User user) {
        User userResult = userService.saveUser(user);
        if (userResult != null) {
            return "SignUpSuccess";
        }

        return "SignUpFailed";
    }

    @RequestMapping(value = "/Login")
    public String userLogin(@ModelAttribute("userName") String userName, @ModelAttribute("userPassword") String userPassword,
                            HttpServletRequest request, HttpServletResponse response) {
        User user = userService.findUserByUserName(userName);
        if (user != null) {
            if (user.getUserName().equals(userName) && user.getUserPassword().equals(userPassword)) {

                //used ribbon for client side load balancing
                ServiceInstance instance = loadBalancerClient.choose(movieservicename);
                URI uri = URI.create(String.format("http://%s:%s",
                        instance.getHost(), instance.getPort()));


                String url = uri.toString() + "/MovieIndex.html";
                try {
                    request.getSession().setAttribute("user", user);
                    System.out.println("Register-Service.loginUser .URI=========" + uri);

                    response.sendRedirect(url);

                } catch (Exception e) {
                    e.printStackTrace();
                    System.out.println("Error in Dispacthing");
                }


            }
            return "LogInFailed";
        }
        return "LogInFailed";
    }

    @RequestMapping(value = "/findAllUser", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public List<User> getAllResiterduser() {
        return userService.findAllUser();
    }

    @RequestMapping(value = "/getAllMovieReview", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public String getAllReview() {
        ServiceInstance instance = loadBalancerClient.choose(reviewservice);
        URI uri = URI.create(String.format("http://%s:%s",
                instance.getHost(), instance.getPort()));


        System.out.println("Review servcie .URI=========" + uri);

        String url = uri.toString() + "/getAllReview";

        System.out.println("====================================");
        System.out.println("Register-Service.loginUser .URI=========" + uri);

        Map<String, Object> aa = new HashMap<String, Object>();

        ResponseEntity<String> entity = restTemplate.getForEntity(url, String.class, aa);

        if (entity.getStatusCode() == HttpStatus.OK) {
            return entity.getBody();
        }

        return null;
    }

    @RequestMapping(value = "/getMovieReview")
    @ResponseBody
    public String getMovieReview(HttpServletRequest request, HttpServletResponse response) {
        ServiceInstance instance = loadBalancerClient.choose(reviewservice);
        URI uri = URI.create(String.format("http://%s:%s",
                instance.getHost(), instance.getPort()));


        System.out.println("Review servcie .URI=========" + uri);

        String url = uri.toString() + "/MovieReview.html";

        System.out.println("====================================");
        System.out.println("Register-Service.loginUser .URI=========" + uri);

        Map<String, Object> aa = new HashMap<String, Object>();

        try {
            response.sendRedirect(url);
        } catch (Exception e) {
            System.out.println("Error in Dispacthing");
        }

        return "";

    }


}
