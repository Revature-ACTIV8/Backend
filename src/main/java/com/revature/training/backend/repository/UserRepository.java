package com.revature.training.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.revature.training.backend.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    
}
