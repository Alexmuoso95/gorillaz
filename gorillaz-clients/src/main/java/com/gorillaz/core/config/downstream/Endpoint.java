package com.gorillaz.core.config.downstream;

import io.netty.handler.codec.http.HttpMethod;
import lombok.Data;

@Data
public class Endpoint {

	private String name;
	private String path;
	private HttpMethod method;
	
}
