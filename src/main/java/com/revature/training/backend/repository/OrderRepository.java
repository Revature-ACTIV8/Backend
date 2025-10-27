package com.revature.training.backend.repository;

import com.revature.training.backend.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    //finds the orders using the customer id
    List<Order> findByCustomerId(Long customerId);
    //finds all orders that are using the same order status
    List<Order> findByOrderStatus(String orderStatus);
}
