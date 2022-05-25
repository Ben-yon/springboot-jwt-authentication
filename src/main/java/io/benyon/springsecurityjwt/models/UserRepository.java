package io.benyon.springsecurityjwt.models;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer>{
  Optional<User> findByUsername(String userName); 
}
