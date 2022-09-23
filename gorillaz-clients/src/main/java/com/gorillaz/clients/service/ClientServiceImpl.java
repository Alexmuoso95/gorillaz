package com.gorillaz.clients.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gorillaz.clients.entity.Client;
import com.gorillaz.clients.mapper.ClientMapper;
import com.gorillaz.clients.repository.ClientCrudRepository;
import com.gorillaz.clients.request.ClientRequest;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ClientServiceImpl  implements ClientService{

	@Autowired
	private ClientCrudRepository clientCrudRepository;
	
	@Autowired
	private ClientMapper clientMapper;
	
	@Override
	public List<Client> insertTwentyRandomClients() {
		List<ClientRequest> clientRequests = new ArrayList<>();
        boolean useLetters = true;
		while(clientRequests.size() < 20) {
			ClientRequest clientRequest = new ClientRequest();
			clientRequest.setName(RandomStringUtils.random(6, useLetters, false)); 
			clientRequest.setMiddleName(RandomStringUtils.random(3, useLetters, false)); 
			clientRequest.setLastName(RandomStringUtils.random(3, useLetters, false)); 
			clientRequests.add(clientRequest);
			log.info("Insert 20 Clients - User Number : {} created",clientRequests.size());
		}
		return (List<Client>) clientCrudRepository.saveAll(clientMapper.mapClientRequest(clientRequests));
	}
	
	@Override
	public Long insertClient(ClientRequest clientRequest) {
		return clientCrudRepository.save(clientMapper.mapClient(clientRequest)).getId();
	}

	@Override
	public Client getClient(Long id) {
		return clientCrudRepository.findById(id).orElse(null);
	}

	@Override
	public List<Client> getClients() {
		return  (List<Client>) clientCrudRepository.findAll();
	}

	@Override
	public Client updateClient(Client client, Long id) {
		client.setId(clientCrudRepository.findById(id).get().getId()); 
		return clientCrudRepository.save(client);
	}

	@Override
	public Client deleteClient(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

}
