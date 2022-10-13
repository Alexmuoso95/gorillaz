package com.gorillaz.core.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;

import com.gorillaz.core.model.entity.Client;
import com.gorillaz.core.model.request.ClientRequest;
import com.gorillaz.core.repository.ClientRepository;
import com.gorillaz.core.service.ClientService;
import com.gorillaz.core.transform.ClientMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@EnableAsync
public class ClientServiceImpl implements ClientService {

	@Autowired
	private ClientRepository clientRepository;

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
			log.info("Insert 20 Clients - UserDTO Number : {} created", clientRequests.size());
		}
		return (List<Client>) clientRepository.saveAll(clientMapper.mapClientRequest(clientRequests));
	}

	@Override
	public Long createClient(ClientRequest clientRequest) {
		return clientRepository.save(clientMapper.mapClientRequest(clientRequest)).getId();
	}

	@Override
	public Client getClient(Long id) {
		return clientRepository.findById(id).orElse(null);
	}

	@Override
	public List<Client> getClients() {
		return clientRepository.findAll();
	}

	@Override
	public Page<Client> getClients(Pageable pageable) {
		return clientRepository.findAll(pageable);
	}

	@Override
	public Client updateClient(ClientRequest clientRequest, Long id) {
		Client client =  clientRepository.findById(id).orElse(null);
		if(client!=null) {
			client.setName(clientRequest.getName());
			client.setLastName(clientRequest.getLastName());
			client.setEmail(clientRequest.getEmail());
			client.setPhoneNumber(clientRequest.getPhoneNumber());
		}
		return clientRepository.save(client);
	}

	@Override
	public void deleteClient(Long id) {
		Client client =  clientRepository.findById(id).orElse(null);
		if(client!=null) {
			clientRepository.delete(client);
		}
	}
	
	@Async
	@Override
	public void deleteAll() {
		clientRepository.deleteAll();
	}

//	@Override
//	public boolean uploadFile(MultipartFile file, Long id) {
//		if(!file.isEmpty(file)) {
//			Client client = clientRepository.findById(id).orElse(null);
//			if(client!=null) {
//				
//			}	
//		}else
//		return false;
//	}

}
