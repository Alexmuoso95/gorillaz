package com.gorillaz.clients.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gorillaz.clients.entity.Client;
import com.gorillaz.clients.repository.ClientCrudRepository;

@Service
public class ClientServiceImpl  implements ClientService{

	@Autowired
	private ClientCrudRepository clientCrudRepository;
	
	@Override
	public Long insertClient(Client client) {
		return  clientCrudRepository.save(client).getId() ;
	}

}
