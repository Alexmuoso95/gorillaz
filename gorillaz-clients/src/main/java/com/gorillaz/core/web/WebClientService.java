package com.gorillaz.core.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.gorillaz.core.model.entity.Client;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class WebClientService {

	private final WebClient webClient;

	@Autowired
	public WebClientService(WebClient.Builder builder) {
		this.webClient = builder.baseUrl("http://localhost:8081").build();
	}
	
	public List<Client> getClients() {
		Flux<Client> clientStream = webClient.get().uri("/v1/clients/100").retrieve().bodyToFlux(Client.class);
		Mono<List<Client>> collectedClientStream = clientStream.collectList();
		List<Client> recipeList = collectedClientStream.block();
		return recipeList;
	}
}
