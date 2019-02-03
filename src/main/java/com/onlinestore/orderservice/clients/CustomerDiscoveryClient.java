package com.onlinestore.orderservice.clients;

import com.onlinestore.orderservice.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
public class CustomerDiscoveryClient {

	private DiscoveryClient discoveryClient;

	private RestTemplate restTemplate;

	@Autowired
	public CustomerDiscoveryClient(DiscoveryClient discoveryClient, RestTemplateBuilder restTemplateBuilder) {
		this.discoveryClient = discoveryClient;
		this.restTemplate = restTemplateBuilder.build();
	}

	public Customer getCustomer(String customerId) {
		List<ServiceInstance> serviceInstances = discoveryClient.getInstances("customerservice");
		if (serviceInstances.size() == 0) return null;
		String serviceUri = String.format("%s/v1/customers/%s", serviceInstances.get(0).getUri().toString(), customerId);

		ResponseEntity<Customer> restExchange =
				restTemplate.exchange(
						serviceUri,
						HttpMethod.GET,
						null, Customer.class, customerId);

		return restExchange.getBody();
	}


}
