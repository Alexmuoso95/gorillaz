package com.gorillaz.core.utils;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.gorillaz.core.config.MetricsConfig;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;

@Component
public class PrometheusMetricsUtils {

	private final MeterRegistry registry; 
	
	@Qualifier("requestCounter")
	private final Counter requestCounter ;
	
	public PrometheusMetricsUtils(MeterRegistry registry, @Qualifier("requestCounter") Counter requestCounter) {
		this.registry = registry;
		this.requestCounter = requestCounter;
	}
	
	public void incrementRequest(String endpoint) {
		this.registry.counter(MetricsConfig.REQUEST_COUNTER, MetricsConfig.ENDPOINT_TAG, endpoint).increment();
	}
}
