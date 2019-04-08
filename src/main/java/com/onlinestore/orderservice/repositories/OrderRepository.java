package com.onlinestore.orderservice.repositories;

import com.onlinestore.orderservice.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Spring Data JPA repository for {@link Order} entity that supports CRUD operations.
 */
@Repository
public interface OrderRepository extends JpaRepository<Order, String> {
	/**
	 * Returns list of orders of the specified customer from database.
	 *
	 * @param customerId unique id of the customer
	 * @return list of orders
	 */
	List<Order> findByCustomerId(String customerId);

	/**
	 * Returns Optional of specific order from database. Returns empty optional when there is no order.
	 *
	 * @param customerId unique id of the customer
	 * @param orderId    unique id of the order
	 * @return Optional of order
	 */
	Optional<Order> findByCustomerIdAndOrderId(String customerId, String orderId);

	/**
	 * Deletes specific order in database.
	 *
	 * @param orderId the unique id of the order
	 */
	void deleteById(String orderId);
}
