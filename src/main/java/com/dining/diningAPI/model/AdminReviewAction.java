package com.dining.diningAPI.model;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
public class AdminReviewAction {
  @NonNull
  private Boolean adminApproves;
}
