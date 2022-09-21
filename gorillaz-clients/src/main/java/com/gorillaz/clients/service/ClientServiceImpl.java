package com.gorillaz.clients.service;

import java.util.Collection;
import java.util.List;

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
