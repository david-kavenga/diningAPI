package com.dining.diningAPI.controller;

import java.util.Optional;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.dining.diningAPI.exceptions.QueryNotSupportedException;
import com.dining.diningAPI.model.*;
import com.dining.diningAPI.repository.DiningReviewRepository;
import com.dining.diningAPI.repository.RestaurantRepository;
import com.dining.diningAPI.repository.UserRepository;

@RestController
public class DiningReviewController {
  DiningReviewRepository diningReviewRepository;
  RestaurantRepository restaurantRepository;
  UserRepository userRepository;

  public DiningReviewController(DiningReviewRepository diningReviewRepository, RestaurantRepository restaurantRepository, UserRepository userRepository){
    this.diningReviewRepository = diningReviewRepository;
    this.restaurantRepository = restaurantRepository;
    this.userRepository = userRepository;
  }

  @GetMapping("/users")
  public Iterable<User> getUsers(){
    return userRepository.findAll();
  }

  @GetMapping("/users/{displayName}")
  public User getUser(@PathVariable String displayName) throws QueryNotSupportedException{
    Optional<User> userOptional = userRepository.findByDisplayName(displayName);
    if(userOptional.isEmpty()){
      throw new QueryNotSupportedException("Error: Query not supported. User of specified display name not found.");
    }

    return userOptional.get();
  }
}
