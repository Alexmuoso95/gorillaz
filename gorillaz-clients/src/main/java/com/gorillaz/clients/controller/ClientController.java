package com.gorillaz.clients.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.gorillaz.clients.entity.Client;
import com.gorillaz.clients.service.ClientService;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@RestController
@RequestMapping("/v1/client")
public class ClientController {

	@Autowired
	private ClientService clientService;
	
	@RequestMapping(method = RequestMethod.GET, path = "/test")
	public ResponseEntity<String> test(){
		log.info("Test Endpoint Executed");
		return new ResponseEntity<>(HttpStatus.ACCEPTED);
	}

	@ResponseStatus(HttpStatus.CREATED)
	@RequestMapping(method = RequestMethod.POST, path = "/insert")
	public Long insertClient(@RequestBody Client client){
		log.info("Insert Client Endpoint Executed");
		return clientService.insertClient(client);
	}

	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(method = RequestMethod.GET, path = "/get/{id}")
	public Mono<Client> getClient(@PathVariable String id,@RequestParam(name = "mode", required = false) String model){
		log.info("GET Client By Id Endpoint Executed");
		return null;
	}
	
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(method = RequestMethod.GET, path = "/get")
	public Flux<Client> getClients(){
		log.info("GET Clients Endpoint Executed");
		return null;
	}

	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(method = RequestMethod.PUT, path = "/update/{id}")
	public Mono<ResponseEntity<Client>> updateClient(@RequestBody Client device, @PathVariable String id){
		log.info("Update Client Endpoint Executed");
		return null;
	}

	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(method = RequestMethod.DELETE, path = "/delete/{id}")
	public Mono<Void> deleteClient(@PathVariable String id){
		log.info("Delete Clients Endpoint Executed");
		return null;
	}

	
}
