package com.wipro.movieuser.repository;


import com.wipro.movieuser.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    @Override
    User  save(User user);


    User findByUserName(String userName);

    @Override
    Iterable<User> findAll();
}
