package com.onlinestore.orderservice.hystrix;

import com.netflix.hystrix.strategy.HystrixPlugins;
import com.netflix.hystrix.strategy.concurrency.HystrixConcurrencyStrategy;
import com.netflix.hystrix.strategy.eventnotifier.HystrixEventNotifier;
import com.netflix.hystrix.strategy.executionhook.HystrixCommandExecutionHook;
import com.netflix.hystrix.strategy.metrics.HystrixMetricsPublisher;
import com.netflix.hystrix.strategy.properties.HystrixPropertiesStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

/**
 * Spring configuration class that rebuilds the Hystrix plugin that manages all the different components
 * running within this service
 */
@Configuration
public class ThreadLocalConfiguration {
	/**
	 * Existing concurrency strategy defined by Spring Cloud
	 */
	private HystrixConcurrencyStrategy existingConcurrencyStrategy;

	/**
	 * Constructs a new {@code ThreadLocalConfiguration} instance.
	 *
	 * @param existingConcurrencyStrategy existing concurrency strategy defined by Spring Cloud
	 */
	@Autowired(required = false)
	public ThreadLocalConfiguration(HystrixConcurrencyStrategy existingConcurrencyStrategy) {
		this.existingConcurrencyStrategy = existingConcurrencyStrategy;
	}

	/**
	 * Registers custom HystrixConcurrencyStrategy and re-registers the original Hystrix components
	 * with the Hystrix plugin.
	 */
	@PostConstruct
	public void init() {
		HystrixEventNotifier eventNotifier = HystrixPlugins.getInstance().getEventNotifier();
		HystrixMetricsPublisher metricsPublisher = HystrixPlugins.getInstance().getMetricsPublisher();
		HystrixPropertiesStrategy propertiesStrategy = HystrixPlugins.getInstance().getPropertiesStrategy();
		HystrixCommandExecutionHook commandExecutionHook = HystrixPlugins.getInstance().getCommandExecutionHook();

		HystrixPlugins.reset();

		HystrixPlugins.getInstance().registerConcurrencyStrategy(new CustomHystrixConcurrencyStrategy(existingConcurrencyStrategy));
		HystrixPlugins.getInstance().registerEventNotifier(eventNotifier);
		HystrixPlugins.getInstance().registerMetricsPublisher(metricsPublisher);
		HystrixPlugins.getInstance().registerPropertiesStrategy(propertiesStrategy);
		HystrixPlugins.getInstance().registerCommandExecutionHook(commandExecutionHook);
	}
}
