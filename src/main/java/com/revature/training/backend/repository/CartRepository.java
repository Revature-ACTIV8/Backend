package com.revature.training.backend.repository;

import com.revature.training.backend.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {
    //finds the cart using the customer id
    Optional<Cart> findByCustomerId(Long customerId);
}
