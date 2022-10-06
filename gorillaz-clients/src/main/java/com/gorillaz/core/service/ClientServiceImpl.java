package com.gorillaz.core.service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;

import com.gorillaz.core.entity.Client;
import com.gorillaz.core.repository.ClientCrudRepository;
import com.gorillaz.core.request.ClientRequest;
import com.gorillaz.core.transform.ClientMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@EnableAsync
public class ClientServiceImpl implements ClientService {

	@Autowired
	private ClientCrudRepository clientCrudRepository;

	@Autowired
	private ClientMapper clientMapper;

	@Override
	public List<Client> insertTwentyRandomClients() {
		List<ClientRequest> clientRequests = new ArrayList<>();
		boolean useLetters = true;
		while (clientRequests.size() < 20) {
			ClientRequest clientRequest = new ClientRequest();
			clientRequest.setName(RandomStringUtils.random(3, useLetters, false));
			clientRequest.setLastName(RandomStringUtils.random(3, useLetters, false));
			clientRequest.setEmail(RandomStringUtils.random(3, useLetters, false)+"@"+RandomStringUtils.random(3, useLetters, false)+".com");
			clientRequest.setPhoneNumber("3121234455");
			clientRequests.add(clientRequest);
			log.info("Insert 20 Clients - User Number : {} created", clientRequests.size());
		}
		return (List<Client>) clientCrudRepository.saveAll(clientMapper.mapClientRequest(clientRequests));
	}

	@Override
	public Long createClient(ClientRequest clientRequest) {
		return clientCrudRepository.save(clientMapper.mapClientRequest(clientRequest)).getId();
	}

	@Override
	public Client getClient(Long id) {
		return clientCrudRepository.findById(id).orElse(null);
	}

	@Override
	public List<Client> getClients() {
		return (List<Client>) clientCrudRepository.findAll();
	}

	@Override
	public Client updateClient(ClientRequest clientRequest, Long id) {
		Client client =  clientCrudRepository.findById(id).orElse(null);
		if(client!=null) {
			client.setName(clientRequest.getName());
			client.setLastName(clientRequest.getLastName());
			client.setEmail(clientRequest.getEmail());
			client.setPhoneNumber(new BigInteger(clientRequest.getPhoneNumber()));
		}
		return clientCrudRepository.save(client);
	}

	@Override
	public void deleteClient(Long id) {
		Client client =  clientCrudRepository.findById(id).orElse(null);
		if(client!=null) {
			clientCrudRepository.delete(client);
		}
	}
	
	@Async
	@Override
	public void deleteAll() {
		clientCrudRepository.deleteAll();
	}

}
