package com.gorillaz.clients.config;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.gorillaz.clients.interceptor.GorillazInterceptor;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@AllArgsConstructor
public class GorillazInterceptorConfig implements WebMvcConfigurer{

	private final GorillazInterceptor interceptor;
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		log.info("::::::::::: INTERCEPTOR - CREATED  :::::::::::" );
		registry.addInterceptor(interceptor);
	}
}
