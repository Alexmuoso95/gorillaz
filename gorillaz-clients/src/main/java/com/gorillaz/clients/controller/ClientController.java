package com.gorillaz.clients.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.gorillaz.clients.entity.Client;
import com.gorillaz.clients.service.ClientService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/v1/client")
public class ClientController {

	@Autowired
	private ClientService clientService;
	
	@ResponseStatus(HttpStatus.CREATED)
	@RequestMapping(method = RequestMethod.POST, path = "/insert")
	public Long insertClient(@RequestBody Client client){
		log.info("Insert Client Endpoint Executed");
		return clientService.insertClient(client);
	}

	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(method = RequestMethod.GET, path = "/get/{id}")
	public ResponseEntity<Client> getClient(@PathVariable Long id){
		log.info("GET Client By Id Endpoint Executed");
		return new ResponseEntity<>(clientService.getClient(id),HttpStatus.OK);
	}
	
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(method = RequestMethod.GET, path = "/get/all")
	public ResponseEntity<List<Client>> getClients(){
		log.info("GET Clients Endpoint Executed");
		return new ResponseEntity<>(clientService.getClients(),HttpStatus.OK);
	}

	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(method = RequestMethod.PUT, path = "/update/{id}")
	public ResponseEntity<Client> updateClient(@RequestBody Client client, @PathVariable Long id){
		log.info("Update Client Endpoint Executed");
		return new ResponseEntity<>(clientService.updateClient(client,id),HttpStatus.OK);
	}

	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(method = RequestMethod.DELETE, path = "/delete/{id}")
	public void deleteClient(@PathVariable Long id){
		log.info("Delete Clients Endpoint Executed");
	}

	
}
