package com.onlinestore.orderservice.clients;

import com.onlinestore.orderservice.domain.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * Implementation of the {@link Client} interface using {@code DiscoveryClient}.
 * <p>
 * Uses discovery client and standard Spring RestTemplate class to invoke customer service.
 */
@Component
public class CustomerDiscoveryClient implements Client {
	/**
	 * DiscoveryClient used to interact with Ribbon.
	 */
	private DiscoveryClient discoveryClient;
	/**
	 * Spring RestTemplate.
	 */
	private RestTemplate restTemplate;

	/**
	 * Constructs new {@code CustomerDiscoveryClient} instance.
	 *
	 * @param discoveryClient DiscoveryClient
	 * @param restTemplate    Spring RestTemplate
	 */
	@Autowired
	public CustomerDiscoveryClient(DiscoveryClient discoveryClient, RestTemplate restTemplate) {
		this.discoveryClient = discoveryClient;
		this.restTemplate = restTemplate;
	}

	/**
	 * Calls customer service via Spring DiscoveryClient.
	 *
	 * @param customerId unique id of the customer
	 * @return customer specified by Id
	 */
	@Override
	public Customer getCustomer(String customerId) {
		List<ServiceInstance> serviceInstances = discoveryClient.getInstances("customerservice");

		if (serviceInstances.size() == 0) return null;
		String serviceUri = String.format("%s/v1/customers/%s", serviceInstances.get(0).getUri().toString(), customerId);

		ResponseEntity<Customer> responseEntity = restTemplate.exchange(
				serviceUri,
				HttpMethod.GET,
				null, Customer.class, customerId);

		return responseEntity.getBody();
	}

}
