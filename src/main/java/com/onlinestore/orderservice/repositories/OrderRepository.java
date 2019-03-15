package com.onlinestore.orderservice.repositories;

import com.onlinestore.orderservice.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, String> {
	List<Order> findByCustomerId(String customerId);
	Order findByCustomerIdAndOrderId(String customerId, String orderId);
	void deleteById(String orderId);
}
