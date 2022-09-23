package com.gorillaz.clients.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.gorillaz.clients.entity.Client;
import com.gorillaz.clients.request.ClientRequest;
import com.gorillaz.clients.service.ClientService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/v1/client")
@Api(value = "Client", description = "Clients Controller Application")
public class ClientController {

	@Autowired
	private ClientService clientService;
	
	@PostMapping(path = "/insertTwentyUsers")
	@ApiOperation(value = "Insert 20 Random clients into the data base")
	@ApiResponse(code = 201, message = "CREATED", response = Client.class)
	public ResponseEntity<?> insertUndredClients() throws Exception{
		log.info("Insert 20 Clients - Endpoint Executed");
		return new ResponseEntity<>(clientService.insertTwentyRandomClients(),HttpStatus.CREATED);
	}
	
	@RequestMapping(method = RequestMethod.POST, path = "/insert")
	public Long insertClient(@RequestBody @Valid ClientRequest client){
		log.info("Insert Client Endpoint Executed");
		return clientService.insertClient(client);
	}

	@ApiOperation(value = "Get Client by id")
	@ApiResponse(code = 200, message = "OK", response = Client.class)
	@RequestMapping(method = RequestMethod.GET, path = "/get/{id}")
	public ResponseEntity<Client> getClient(@PathVariable Long id){
		log.info("GET Client By Id Endpoint Executed");
		return new ResponseEntity<>(clientService.getClient(id),HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.GET, path = "/get/all")
	public ResponseEntity<List<Client>> getClients(){
		log.info("GET Clients Endpoint Executed");
		return new ResponseEntity<>(clientService.getClients(),HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.PUT, path = "/update/{id}")
	public ResponseEntity<Client> updateClient(@RequestBody Client client, @PathVariable Long id){
		log.info("Update Client Endpoint Executed");
		return new ResponseEntity<>(clientService.updateClient(client,id),HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.DELETE, path = "/delete/{id}")
	public void deleteClient(@PathVariable Long id){
		log.info("Delete Clients Endpoint Executed");
	}
	
	@ResponseStatus(code = HttpStatus.OK)
	@ApiOperation(value = "Delete All the Clients")
	@RequestMapping(method = RequestMethod.DELETE, path = "/delete/all")
	public void deleteAll(){
		log.info("Delete All Clients Endpoint Executed");
		clientService.deleteAll();
	}
	
}
