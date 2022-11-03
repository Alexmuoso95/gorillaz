package com.gorillaz.core.config.downstream;

import java.util.Map;

import lombok.Data;

@Data
public class DownStreamService {
	private String baseUrl;
	private Map<String,Endpoint> endpoints;
}
