package com.dining.diningAPI.model;

import javax.persistence.Column;
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
public class Restaurant {
  @Id
  @GeneratedValue(strategy=GenerationType.IDENTITY)
  private Long id;

  @NonNull
  private String name;

  @NonNull
  private String city;

  @NonNull
  private String state;

  @NonNull
  private String zipCode;

  private Float overallScore = null;
  private Float peanutScore = null;
  private Float eggScore = null;
  private Float dairyScore = null;
  
  @Column(columnDefinition = "integer default 0") private Integer peanutScoreSum;
  @Column(columnDefinition = "integer default 0") private Integer eggScoreSum;
  @Column(columnDefinition = "integer default 0") private Integer dairyScoreSum;
  @Column(columnDefinition = "integer default 0") private Integer peanutResponseCount;
  @Column(columnDefinition = "integer default 0") private Integer eggResponseCount;
  @Column(columnDefinition = "integer default 0") private Integer dairyResponseCount;
}
