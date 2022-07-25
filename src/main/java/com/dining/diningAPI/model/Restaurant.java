package com.dining.diningAPI.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@RequiredArgsConstructor
public class Restaurant {
  @Id
  @GeneratedValue
  private Long id;

  @NonNull
  private String name;
  
  private Float overallScore = null;
  private Float peanutScore = null;
  private Float eggScore = null;
  private Float dairyScore = null;
  private Integer peanutScoreSum;
  private Integer eggScoreSum;
  private Integer dairyScoreSum;
  private Integer peanutResponseCount;
  private Integer eggResposeCount;
  private Integer dairyResponseCount;
}
