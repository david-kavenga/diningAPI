package com.dining.diningAPI.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.NonNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
@RequiredArgsConstructor
@Table(name = "Users")
public class User {
  @Id
  @GeneratedValue(strategy=GenerationType.IDENTITY)
  private Long id;

  @NonNull
  @Column(unique=true)
  @Setter(value = AccessLevel.NONE)
  private String displayName;

  @NonNull
  private String city;

  @NonNull
  private String state;

  @NonNull
  private String zipcode;

  private Boolean peanutAllergyInterest;
  private Boolean eggAllergyInterest;
  private Boolean dairyAllergyInterest;
}
