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
import com.dining.diningAPI.model.DiningReview.Status;
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
        return restaurantRepository.findByZipcodeAndPeanutScoreIsNotNullOrderByPeanutScoreDesc(zipcode);
      case "egg":
        return restaurantRepository.findByZipcodeAndEggScoreIsNotNullOrderByEggScoreDesc(zipcode);
      case "dairy":
        return restaurantRepository.findByZipcodeAndDairyScoreIsNotNullOrderByDairyScoreDesc(zipcode);
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

  @PostMapping("/users/{displayName}/review")
  public DiningReview addReview(@RequestBody DiningReview newReview, @PathVariable String displayName){
    if(!isValidUser(displayName)){
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "DisplayName specified does not match a user");
    }
    if(!isValidRestaurant(newReview.getRestaurantId())){
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "DisplayName specified does not match a restaurant");
    }
    if(newReview.getZipcode() == null || (newReview.getPeanutScore() == null && newReview.getEggScore() == null && newReview.getDairyScore() == null)){
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "One or more required inputs were null");
    }

    newReview.setDisplayName(displayName);
    newReview.setReviewStatus(Status.PENDING);

    return diningReviewRepository.save(newReview);
  }

  @GetMapping("/admin/reviews/pending")
  public Iterable<DiningReview> getPendingReviews(){
    return diningReviewRepository.findAllByReviewStatus(Status.PENDING);
  }

  @PutMapping("/admin/reviews/pending")
  public DiningReview updateReview(@RequestBody DiningReview newReview){
    Optional<DiningReview> diningReviewOptional = diningReviewRepository.findById(newReview.getId());
    if(!diningReviewOptional.isPresent()){
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "DisplayName specified does not match a user");
    }

    DiningReview updateReview = diningReviewOptional.get();

    if(newReview.getReviewStatus() == null){
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Missing approval status");
    }

    updateReview.setReviewStatus(newReview.getReviewStatus());

    DiningReview completedReview = diningReviewRepository.save(updateReview);

    if(updateReview.getReviewStatus() == Status.ACCEPTED){
      updateRestaraurantScores(completedReview.getRestaurantId());
    }

    return completedReview;
  }

  public Boolean isValidUser(String displayName){
    Optional<User> userOptional = userRepository.findByDisplayName(displayName);
    return (!userOptional.isEmpty());
  }

  public Boolean isValidRestaurant(Long id){
    Optional<Restaurant> restaurantOptional = restaurantRepository.findById(id);
    return (!restaurantOptional.isEmpty());
  }

  public void updateRestaraurantScores(Long id){
    Iterable<DiningReview> approvedReviews = getApprovedReviewsByRestaurant(id);
    Optional<Restaurant> restaurantOptional = restaurantRepository.findById(id);
    Restaurant restaurant = restaurantOptional.get();

    Float peanutScoreSum = 0f;
    Float eggScoreSum = 0f;
    Float dairyScoreSum = 0f;
    Integer peanutResponseCount = 0;
    Integer eggResponseCount = 0;
    Integer dairyResponseCount = 0;

    for(DiningReview review : approvedReviews){
      if(review.getPeanutScore() != null){
        peanutResponseCount++;
        peanutScoreSum += review.getPeanutScore();
      }
      if(review.getEggScore() != null){
        eggResponseCount++;
        eggScoreSum += review.getEggScore();
      }
      if(review.getDairyScore() != null){
        dairyResponseCount++;
        dairyScoreSum += review.getDairyScore();
      }
    }

    if(peanutResponseCount > 0){
      restaurant.setPeanutScore((peanutScoreSum/peanutResponseCount));
    }
    if(eggResponseCount > 0){
      restaurant.setEggScore((eggScoreSum/eggResponseCount));
    }
    if(eggResponseCount > 0){
      restaurant.setDairyScore((dairyScoreSum/dairyResponseCount));
    }
    if((peanutResponseCount + eggResponseCount + dairyResponseCount) > 0){
      restaurant.setOverallScore((peanutScoreSum + eggScoreSum + dairyScoreSum)/(peanutResponseCount + eggResponseCount + dairyResponseCount));
    }
    
    restaurantRepository.save(restaurant);
  }

  public Iterable<DiningReview> getApprovedReviewsByRestaurant(Long id){
    return diningReviewRepository.findAllByRestaurantIdAndReviewStatus(id, Status.ACCEPTED);
  }
}
