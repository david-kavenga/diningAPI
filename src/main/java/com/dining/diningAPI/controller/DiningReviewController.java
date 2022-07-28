package com.dining.diningAPI.controller;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

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
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "DisplayName specified does not match a user");
    }
    return userOptional.get();
  }

  @PostMapping("/users")
  public User addUser(@RequestBody User newUser){
    if(newUser.getDisplayName() == null || newUser.getCity() == null || newUser.getState() == null || newUser.getZipcode() == null || newUser.getPeanutAllergyInterest() == null || newUser.getEggAllergyInterest() == null || newUser.getDairyAllergyInterest() == null){
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "One or more required inputs were null");
    }
    return userRepository.save(newUser);
  }

  @PutMapping("/users/{displayName}")
  public User updateUser(@RequestBody User newUser, @PathVariable String displayName){
    Optional<User> userOptional = userRepository.findByDisplayName(displayName);
    if(!userOptional.isPresent()){
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "DisplayName specified does not match a user");
    }

    User userToUpdate = userOptional.get();

    if(newUser.getCity() != null){
      userToUpdate.setCity(newUser.getCity());
    }
    if(newUser.getState() != null){
      userToUpdate.setState(newUser.getState());
    }
    if(newUser.getZipcode() != null){
      userToUpdate.setZipcode(newUser.getZipcode());
    }
    if(newUser.getPeanutAllergyInterest() != null){
      userToUpdate.setPeanutAllergyInterest(newUser.getPeanutAllergyInterest());
    }
    if(newUser.getEggAllergyInterest() != null){
      userToUpdate.setEggAllergyInterest(newUser.getEggAllergyInterest());
    }
    if(newUser.getDairyAllergyInterest() != null){
      userToUpdate.setDairyAllergyInterest(newUser.getDairyAllergyInterest());
    }

    userRepository.save(userToUpdate);
    return userToUpdate;
  }

  @GetMapping("/restaurants")
  public Iterable<Restaurant> getRestaurants(){
    return restaurantRepository.findAll();
  }

  @GetMapping("/restaurants/{id}")
  public Restaurant getRestaurantById(@PathVariable Long id){
    Optional<Restaurant> restaurantOptional = restaurantRepository.findById(id);
    if(restaurantOptional.isEmpty()){
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No restaurant with specified id");
    }
    return restaurantOptional.get();
  }

  @GetMapping("/restaurants/search")
  public Iterable<Restaurant> getRestaurantsByZipcode(@RequestParam(name="zipcode", required = true) String zipcode, @RequestParam(name="allergy", required = true) String allergy){
    switch(allergy){
      case "peanut": 
        return restaurantRepository.findByZipcodeAndPeanutResponseCountGreaterThanOrderByPeanutScoreDesc(zipcode, 0);
      case "egg":
        return restaurantRepository.findByZipcodeAndEggResponseCountGreaterThanOrderByEggScoreDesc(zipcode, 0);
      case "dairy":
        return restaurantRepository.findByZipcodeAndDairyResponseCountGreaterThanOrderByDairyScoreDesc(zipcode, 0);
      default:
        return restaurantRepository.findByZipcode(zipcode);
    }
    
  }

  @PostMapping("/restaurants")
  public Restaurant addRestaurant(@RequestBody Restaurant newRestaurant){
    if(newRestaurant.getName() == null || newRestaurant.getCity() == null || newRestaurant.getState() == null || newRestaurant.getZipcode() == null){
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "One or more required inputs were null");
    }
    Optional<Restaurant> optionalRestaurant = restaurantRepository.findByNameAndZipcode(newRestaurant.getName(), newRestaurant.getZipcode());
    if(optionalRestaurant.isPresent()){
      throw new ResponseStatusException(HttpStatus.CONFLICT, "A restaurant already exists with the same name and zipcode");
    }

    return restaurantRepository.save(newRestaurant);
  }

  public Boolean isValidUser(String displayName){
    Optional<User> userOptional = userRepository.findByDisplayName(displayName);
    return (!userOptional.isEmpty());
  }
}
