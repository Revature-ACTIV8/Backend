package com.revature.training.backend.repository;

import com.revature.training.backend.model.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    //finds the cart items using the cart id
    List<CartItem> findByCart_OrderId(Long orderId);
}