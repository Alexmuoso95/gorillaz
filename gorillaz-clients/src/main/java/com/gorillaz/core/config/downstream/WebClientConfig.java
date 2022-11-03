package com.gorillaz.core.config.downstream;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.ClientHttpConnector;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;

import io.netty.channel.ChannelOption;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import lombok.RequiredArgsConstructor;
import reactor.netty.http.client.HttpClient;

@Configuration
@RequiredArgsConstructor
public class WebClientConfig {
	private final DownStreamServicesProperties downStreamServicesProperties ;
	private final WebClient.Builder webClientBuilder;
	
	@Bean("encryperDownstreamService")
	public WebClient encryperDownstreamService() {
		String baseUrl = downStreamServicesProperties.getServices().get("encryper").getBaseUrl();
		String path = downStreamServicesProperties.getServices().get("encryper").getEndpoints().get("getEncryper").getPath();
		return webClientBuilder
				.clone()
				.clientConnector(getClientConnector())
				.baseUrl(baseUrl+path)
				.defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
				.build();
	}
	public ClientHttpConnector getClientConnector() {
		HttpClient httpClient = HttpClient.create()
				  .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 5000)
				  .responseTimeout(Duration.ofMillis(5000))
				  .doOnConnected(conn -> 
				    conn.addHandlerLast(new ReadTimeoutHandler(5000, TimeUnit.MILLISECONDS))
				      .addHandlerLast(new WriteTimeoutHandler(5000, TimeUnit.MILLISECONDS)));

				return new ReactorClientHttpConnector(httpClient.wiretap(true));
	}

}
