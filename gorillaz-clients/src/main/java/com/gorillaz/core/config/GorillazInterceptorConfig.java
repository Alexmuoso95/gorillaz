package com.gorillaz.core.config;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.gorillaz.core.interceptor.GorillazInterceptor;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class GorillazInterceptorConfig implements WebMvcConfigurer{

	private final GorillazInterceptor interceptor;
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(interceptor);
	}
}
