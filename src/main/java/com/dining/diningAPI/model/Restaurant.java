package com.dining.diningAPI.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Restaurant {
  @Id
  @GeneratedValue
  private Long id;

  private Float overallScore;
  private Float peanutScore;
  private Float eggScore;
  private Float dairyScore;
  private Integer peanutScoreSum;
  private Integer eggScoreSum;
  private Integer dairyScoreSum;
  private Integer peanutResponseCount;
  private Integer eggResposeCount;
  private Integer dairyResponseCount;

  public Restaurant() {
    this.overallScore = null;
    this.peanutScore = null;
    this.eggScore = null;
    this.dairyResponseCount = null;
  }
}
