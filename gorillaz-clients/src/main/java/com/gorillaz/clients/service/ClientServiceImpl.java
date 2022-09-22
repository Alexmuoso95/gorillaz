package com.gorillaz.clients.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gorillaz.clients.entity.Client;
import com.gorillaz.clients.repository.ClientCrudRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ClientServiceImpl  implements ClientService{

	@Autowired
	private ClientCrudRepository clientCrudRepository;
	
	@Override
	public List<Client> insertTwentyRandomClients() {
		List<Client> clients = new ArrayList<>();
        boolean useLetters = true;
		while(clients.size() < 20) {
		    Client client = new Client();
	        client.setName(RandomStringUtils.random(6, useLetters, false)); 
	        client.setMiddleName(RandomStringUtils.random(3, useLetters, false)); 
	        client.setLastName(RandomStringUtils.random(3, useLetters, false)); 
	        clients.add(client);
			log.info("Insert 20 Clients - User Number : {} created",clients.size());
		}
		return (List<Client>) clientCrudRepository.saveAll(clients);
	}
	
	@Override
	public Long insertClient(Client client) {
		return clientCrudRepository.save(client).getId();
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
