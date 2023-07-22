package com.erkan.springauthservice.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.erkan.springauthservice.entity.User;

@Repository
public interface UserRepo extends JpaRepository<User, Long>{
    
    Optional<User> findByUsername(String username);     
}
