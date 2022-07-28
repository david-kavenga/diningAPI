package com.dining.diningAPI.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.dining.diningAPI.model.*;

@Repository
public interface RestaurantRepository extends CrudRepository<Restaurant, Long> {
  public Optional<Restaurant> findByNameAndZipCode(String name, String zipCode);
  public List<Restaurant> findByZipCodeAndPeanutResponseCountGreaterThanOrderByPeanutScoreDesc(String zipCode, Integer count);
  public List<Restaurant> findByZipCodeAndEggResponseCountGreaterThanOrderByEggScoreDesc(String zipCode, Integer count);
  public List<Restaurant> findByZipCodeAndDairyResponseCountGreaterThanOrderByDairyScoreDesc(String zipCode, Integer count);
}
