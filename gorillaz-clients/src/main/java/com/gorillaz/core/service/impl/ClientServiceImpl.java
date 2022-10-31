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

import com.gorillaz.core.enums.ExpandEnum;
import com.gorillaz.core.model.entity.Address;
import com.gorillaz.core.model.entity.Client;
import com.gorillaz.core.model.request.ClientRequest;
import com.gorillaz.core.model.response.ClientResponse;
import com.gorillaz.core.repository.AddressDAO;
import com.gorillaz.core.repository.ClientDAO;
import com.gorillaz.core.service.ClientService;
import com.gorillaz.core.transform.ClientMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@EnableAsync
public class ClientServiceImpl implements ClientService {

	@Autowired
	private ClientDAO clientDAO;

	@Autowired
	private AddressDAO addressDao;
	
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
		return (List<Client>) clientDAO.saveAll(clientMapper.mapClientRequest(clientRequests));
	}

	@Override
	public Long createClient(ClientRequest clientRequest) {
		return clientDAO.save(clientMapper.mapClientRequest(clientRequest)).getId();
	}

	@Override
	public ClientResponse getClient(Long id, List<ExpandEnum> expands) {
		Client client = clientDAO.findById(id).orElse(null);
		ClientResponse clientResponse = new ClientResponse();
		clientResponse.setId(client.getId());
		clientResponse.setName(client.getName());
		clientResponse.setLastName(client.getLastName());
		clientResponse.setPhoneNumber(client.getPhoneNumber());
		clientResponse.setEmail(client.getEmail());
		if(expands.contains(ExpandEnum.CLIENT_ADDRESSES)) {
			List<Address> addresses = addressDao.findAllByClientId(id); 
			clientResponse.setAddresses(addresses);
		}
		return clientResponse;
	}

	@Override
	public List<Client> getClients() {
		return clientDAO.findAll();
	}

	@Override
	public Page<Client> getClients(Pageable pageable) {
		return clientDAO.findAll(pageable);
	}

	@Override
	public Client updateClient(ClientRequest clientRequest, Long id) {
		Client client =  clientDAO.findById(id).orElse(null);
		if(client!=null) {
			client.setName(clientRequest.getName());
			client.setLastName(clientRequest.getLastName());
			client.setEmail(clientRequest.getEmail());
			client.setPhoneNumber(clientRequest.getPhoneNumber());
		}
		return clientDAO.save(client);
	}

	@Override
	public void deleteClient(Long id) {
		Client client =  clientDAO.findById(id).orElse(null);
		if(client!=null) {
			clientDAO.delete(client);
		}
	}
	
	@Async
	@Override
	public void deleteAll() {
		clientDAO.deleteAll();
	}

	@Override
	public ClientResponse getClientsWithAddresses(Long clientId) 
	{
		Client client = clientDAO.findById(clientId).orElse(null);
		ClientResponse clientResponse = new ClientResponse();
		clientResponse.setId(client.getId());
		clientResponse.setName(client.getName());
		clientResponse.setLastName(client.getLastName());
		clientResponse.setPhoneNumber(client.getPhoneNumber());
		clientResponse.setEmail(client.getEmail());
		List<Address> addresses = addressDao.findAllByClientId(clientId); 
		clientResponse.setAddresses(addresses);
		return clientResponse;
	}

//	@Override
//	public boolean uploadFile(MultipartFile file, Long id) {
//		if(!file.isEmpty(file)) {
//			Client client = clientDAO.findById(id).orElse(null);
//			if(client!=null) {
//				
//			}	
//		}else
//		return false;
//	}

}
