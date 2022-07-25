package com.dining.diningAPI.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.dining.diningAPI.model.*;

@Repository
public interface UserRepository extends CrudRepository<User, Long>{
  public Optional<User> findByDisplayName(String displayName);
  public Boolean existsUserByDisplayName(String displayName);
}
