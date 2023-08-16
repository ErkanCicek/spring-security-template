package com.erkan.springauthservice.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.erkan.springauthservice.entity.User;

import jakarta.persistence.Tuple;
import jakarta.transaction.Transactional;

@Repository
@Transactional
public interface UserRepo extends JpaRepository<User, Long>{
    
    Optional<User> findByUsername(String username);

    @Query(nativeQuery = true, value = """
        SELECT 
            CASE WHEN EXISTS 
            (SELECT * FROM users WHERE username = ?1) THEN 1 
            ELSE 0 END AS usernameTaken, 
            CASE WHEN EXISTS (SELECT * FROM users WHERE email = ?2) THEN 1 
            ELSE 0 END AS emailTaken FROM users LIMIT 1
        """)
    Tuple isUsernameAndEmailTaken(String username, String email);
}
