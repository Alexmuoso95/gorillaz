package com.gorillaz.clients.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.gorillaz.clients.model.response.Client;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequestMapping("/v1/client")
public class ClientController {

	@ResponseStatus(HttpStatus.CREATED)
	@RequestMapping(method = RequestMethod.POST, path = "/insert")
	public Mono<Client> insertClient(@RequestBody Client client){
		return null;
	}

	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(method = RequestMethod.GET, path = "/get/client/{id}")
	public Mono<Client> getClient(@PathVariable String id,@RequestParam(name = "mode", required = false) String model){
		return null;
	}
	
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(method = RequestMethod.GET, path = "/get/clients")
	public Flux<Client> getClients(){
		return null;
	}

	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(method = RequestMethod.PUT, path = "/update/client/{id}")
	public Mono<ResponseEntity<Client>> updateClient(@RequestBody Client device, @PathVariable String id){
		return null;
	}

	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(method = RequestMethod.DELETE, path = "/delete/{id}")
	public Mono<Void> deleteClient(@PathVariable String id){
		return null;
	}

	
}
