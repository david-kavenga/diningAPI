package com.dining.diningAPI.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
  public enum Status {PENDING, ACCEPTED, REJECTED};

  @Id
  @GeneratedValue(strategy=GenerationType.IDENTITY)
  private Long id;

  @NonNull
  private String displayName;

  @NonNull
  private String zipCode;

  @NonNull
  private Long restaurantId;

  private Status reviewStatus = Status.PENDING;
  private Integer peanutScore;
  private Integer eggScore;
  private Integer dairyScore;
  private String commentary;
}
