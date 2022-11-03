package com.gorillaz.core.web;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.reactive.ClientHttpConnector;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;

import com.gorillaz.core.config.downstream.DownStreamServicesProperties;
import com.gorillaz.core.exceptions.webclient.WebClientExceptions;
import com.gorillaz.core.model.entity.Client;

import io.netty.channel.ChannelOption;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.netty.http.client.HttpClient;

@Slf4j
@Service
public class WebClientService {
	
	@Autowired
	DownStreamServicesProperties downStreamServicesProperties ;
	
	@Autowired
	@Qualifier("encryperDownstreamService")
	private WebClient encryperWebclient;
	
	@Retryable(maxAttempts = 5)
	public List<Client> getClientsEncryperCall(Client client) {
		log.info("Web Client - Calling encryper ");
		List<Client> recipeList = new ArrayList<>();
		try {
			Flux<Client> clientStream = 
					encryperWebclient
					.post()
					.body(Mono.just(client), Client.class)
					.retrieve()
					.onStatus(HttpStatus::is4xxClientError, this::handleError)
					.onStatus(HttpStatus::is5xxServerError, this::handleError)
					.bodyToFlux(Client.class);
			Mono<List<Client>> collectedClientStream = clientStream.collectList();
			recipeList = collectedClientStream.block();
		} catch (RuntimeException e) {
			throw new WebClientExceptions(e.getMessage());
		}
		return recipeList;
	}
	public Mono<? extends Throwable> handleError(ClientResponse response){
		return Mono.error(new WebClientExceptions("Call to encryped service was " + response.statusCode()));
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
