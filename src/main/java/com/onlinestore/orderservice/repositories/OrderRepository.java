package com.onlinestore.orderservice.repositories;

import com.onlinestore.orderservice.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, String> {
	List<Order> findByOrderId(String orderId);
	Order findByCustomerIdAndOrderId(String customerId, String orderId);
}
