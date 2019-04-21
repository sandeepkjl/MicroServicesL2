package com.wipro.movieuser.service;

import com.wipro.movieuser.entity.User;
import com.wipro.movieuser.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserService {

    @Autowired
    private UserRepository userRepository ;

    public List<User> findAllUser()
    {
        Iterable<User> allUser = userRepository.findAll();
        List<User>  users= new ArrayList<User>();
        for(User user : allUser )
        {
            users.add(user);
        }

        return users;
    }

    public User findUserByUserName(String userName)
    {
        return userRepository.findByUserName(userName);

    }

    public User saveUser(User user)
    {
        return userRepository.save(user);
    }


}
