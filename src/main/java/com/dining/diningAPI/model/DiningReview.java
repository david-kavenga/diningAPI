package com.dining.diningAPI.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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
  private String zipcode;

  @NonNull
  private Long restaurantId;

  @Column(length = 32, columnDefinition = "varchar(32) default 'PENDING'")
  @Enumerated(value = EnumType.STRING)
  private Status reviewStatus = Status.PENDING;
  private Integer peanutScore;
  private Integer eggScore;
  private Integer dairyScore;
  private String commentary;
}
