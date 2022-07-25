package com.dining.diningAPI.controller;

import org.springframework.web.bind.annotation.RestController;

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
}
