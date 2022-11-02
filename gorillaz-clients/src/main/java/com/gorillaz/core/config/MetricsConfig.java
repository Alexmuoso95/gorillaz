package com.gorillaz.core.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;

@Configuration
public class MetricsConfig {

	private final MeterRegistry meterRegistry;
	public static final String ENDPOINT_TAG = "endpoint";
	public static final String REQUEST_COUNTER = "gorillaz_http_requests";
	
	//  Initialize Meter Registry
	@Autowired
	public MetricsConfig(@Lazy MeterRegistry registry) {
		this.meterRegistry = registry;
	}
	
	//  Create Counter Bean
	@Bean(name = "requestCounter")
	public Counter requestCounter () {
		return this.meterRegistry.counter(REQUEST_COUNTER, ENDPOINT_TAG, "/");
	}
}
