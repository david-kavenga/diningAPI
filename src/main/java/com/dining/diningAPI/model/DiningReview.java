package com.dining.diningAPI.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.NonNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
@RequiredArgsConstructor
public class DiningReview {
  @Id
  @GeneratedValue
  private Long id;

  @NonNull
  private String displayName;

  @NonNull
  private Long restaurantId;

  private Integer peanutScore;
  private Integer eggScore;
  private Integer dairyScore;
  private String commentary;
}
