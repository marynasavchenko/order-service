package com.onlinestore.orderservice.repositories;

import com.onlinestore.orderservice.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Spring Data JPA repository for Order entity that supports CRUD operations.
 */
@Repository
public interface OrderRepository extends JpaRepository<Order, String> {
	/**
	 * Looks up order of specified customer in database.
	 *
	 * @param customerId the unique id of the customer
	 * @return list of orders
	 */
	List<Order> findByCustomerId(String customerId);

	/**
	 * Looks up specific order in database.
	 *
	 * @param customerId the unique id of the customer
	 * @param orderId    the unique id of the order
	 * @return an order
	 */
	Optional<Order> findByCustomerIdAndOrderId(String customerId, String orderId);

	/**
	 * Deletes specific order in database.
	 *
	 * @param orderId the unique id of the order
	 */
	void deleteById(String orderId);
}
