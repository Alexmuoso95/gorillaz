package com.gorillaz.clients.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.AsyncHandlerInterceptor;

import com.gorillaz.clients.utils.PrometheusMetricsUtils;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@AllArgsConstructor
public class GorillazInterceptor implements AsyncHandlerInterceptor{
	
	@Autowired
	private final PrometheusMetricsUtils metrics;
	private static final String CREATE_CLIENT_ENDPOINT = "/v1/client/insert";

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		if(request.getRequestURI().contains(CREATE_CLIENT_ENDPOINT)) {
			log.info(":::::: INTERCEPTOR - v1/client/insert ::::::" );
			metrics.incrementRequest(CREATE_CLIENT_ENDPOINT);
		}
		return true;
	}
}
