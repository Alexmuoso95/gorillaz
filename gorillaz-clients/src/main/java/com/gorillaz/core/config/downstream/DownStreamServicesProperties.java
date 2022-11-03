
package com.gorillaz.core.config.downstream;

import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

@Data
@Configuration 
@ConfigurationProperties("downstream")
public class DownStreamServicesProperties {
	private final Map<String,DownStreamService> services = new HashMap<>();
}
