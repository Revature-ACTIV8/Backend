package com.revature.training.backend.repository;

import com.revature.training.backend.model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
    //finds the order item using the order id
    List<OrderItem> findByOrder_OrderId(Long orderId);
}
