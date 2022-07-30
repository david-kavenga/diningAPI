package com.dining.diningAPI.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.dining.diningAPI.model.*;

@Repository
public interface DiningReviewRepository extends CrudRepository<DiningReview, Long> {
  public List<DiningReview> findAllByReviewStatus(DiningReview.Status reviewStatus);
  public List<DiningReview> findAllByRestaurantIdAndReviewStatus(Long id, DiningReview.Status reviewStatus);
}
