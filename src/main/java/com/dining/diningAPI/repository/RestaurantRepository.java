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
  public List<Restaurant> findByZipcodeAndPeanutResponseCountGreaterThanOrderByPeanutScoreDesc(String zipcode, Integer count);
  public List<Restaurant> findByZipcodeAndEggResponseCountGreaterThanOrderByEggScoreDesc(String zipcode, Integer count);
  public List<Restaurant> findByZipcodeAndDairyResponseCountGreaterThanOrderByDairyScoreDesc(String zipcode, Integer count);
}
