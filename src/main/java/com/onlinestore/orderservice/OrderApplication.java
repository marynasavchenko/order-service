package com.onlinestore.orderservice;

import com.onlinestore.orderservice.contextinfoutils.UserContextInterceptor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;

/**
 * Class that bootstraps this service.
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableCircuitBreaker
public class OrderApplication {

	/**
	 * Creates {@code RestTemplate} and adds {@code UserContextInterceptor} to it.
	 * This {@code RestTemplate} is going to use Ribbon.
	 *
	 * @return {@code RestTemplate}
	 */
	@LoadBalanced
	@Bean
	public RestTemplate getRestTemplate() {
		RestTemplate restTemplate = new RestTemplate();
		List interceptors = restTemplate.getInterceptors();
		if (interceptors == null) {
			restTemplate.setInterceptors(Collections.singletonList(new UserContextInterceptor()));
		} else {
			interceptors.add(new UserContextInterceptor());
			restTemplate.setInterceptors(interceptors);
		}
		return restTemplate;
	}

	/**
	 * Main method, used to run this application.
	 *
	 * @param args the string array, that contains command line arguments.
	 */
	public static void main(String[] args) {
		SpringApplication.run(OrderApplication.class, args);
	}
}
