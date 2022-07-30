package com.dining.diningAPI.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.dining.diningAPI.model.*;

@Repository
public interface RestaurantRepository extends CrudRepository<Restaurant, Long> {
  public Optional<Restaurant> findByNameAndZipcode(String name, String zipcode);
  public List<Restaurant> findByZipcode(String zipcode);
  public List<Restaurant> findByZipcodeAndPeanutScoreIsNotNullOrderByPeanutScoreDesc(String zipcode);
  public List<Restaurant> findByZipcodeAndEggScoreIsNotNullOrderByEggScoreDesc(String zipcode);
  public List<Restaurant> findByZipcodeAndDairyScoreIsNotNullOrderByDairyScoreDesc(String zipcode);
}
