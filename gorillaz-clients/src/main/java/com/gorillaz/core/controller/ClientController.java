package com.gorillaz.core.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gorillaz.core.model.entity.Client;
import com.gorillaz.core.model.request.ClientRequest;
import com.gorillaz.core.model.response.ResponseMessage;
import com.gorillaz.core.service.ClientService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/v1/clients")
@CrossOrigin(origins= {"http://localhost:4200"})
@Api(value = "Client Api")
public class ClientController {

	@Autowired
	private ClientService clientService;
	
	@Secured({"ROLE_ADMIN"})
	@PostMapping("/insertTwentyUsers")
	public ResponseEntity<?> insertUndredClients() throws Exception{
		return new ResponseEntity<>(clientService.insertTwentyRandomClients(),HttpStatus.CREATED);
	}
	
	@GetMapping
	@ApiOperation(value = "Get Client by id")
	public ResponseEntity<?> getClients(){
		return new ResponseEntity<>(clientService.getClients(),HttpStatus.OK);
	}
	
	@GetMapping("/page/{page}")
	public ResponseEntity<?> getClients(@PathVariable Integer page){
		return new ResponseEntity<>(clientService.getClients(PageRequest.of(page, 8)),HttpStatus.OK);
	}
	
//	@PostMapping("/upload")
//	public ResponseEntity<?> uploadU(@RequestParam("file") MultipartFile file , Long id){
//		return new ResponseEntity<>(clientService.uploadFile(file,id),HttpStatus.CREATED);
//	}
	
	@PostMapping
	public ResponseEntity<?> createClient( @Valid @RequestBody ClientRequest client){
		return new ResponseEntity<>(clientService.createClient(client),HttpStatus.CREATED);
	}

	
	@GetMapping("/{id}")
	public ResponseEntity<?> getClient(@PathVariable Long id){
		Client clientResponse = clientService.getClient(id);
		if(clientResponse==null) {
			 return new ResponseEntity<>(new ResponseMessage("El cliente con el id ".concat(id.toString()).concat(" no existe."),404),HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(clientService.getClient(id),HttpStatus.OK);
	}
	
	@Secured({"ROLE_ADMIN"})
	@PutMapping("/{id}")
	public ResponseEntity<?> updateClient(@Valid @RequestBody ClientRequest client, @PathVariable Long id){
		return new ResponseEntity<>(clientService.updateClient(client,id),HttpStatus.OK);
	}


	@Secured({"ROLE_ADMIN"})
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteClient(@PathVariable Long id) {
		log.info("User DELETED ");
		clientService.deleteClient(id);
		return new ResponseEntity<>(new ResponseMessage("El cliente con el id ".concat(id.toString()).concat(" fue eliminado con exito."), 200), HttpStatus.OK);
	}
	
	@Secured({"ROLE_ADMIN"})
	@DeleteMapping("all")
	public ResponseEntity<?> deleteAll(){
		log.info("User DELETED ");
		clientService.deleteAll();
		return new ResponseEntity<>(new ResponseMessage("TODOS LOS CLIENTES FUEORN ELIMINADOS",200),HttpStatus.OK);
	}
	
}
