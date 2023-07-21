package com.erkan.springauthservice.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.erkan.springauthservice.entity.User;

@Repository
public interface UserRepo extends CrudRepository<User, Long>{
    
}
