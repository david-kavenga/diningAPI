package com.dining.diningAPI.exceptions;

public class QueryNotSupportedException extends Exception {
  public QueryNotSupportedException(String message){
    super(message);
  }
}