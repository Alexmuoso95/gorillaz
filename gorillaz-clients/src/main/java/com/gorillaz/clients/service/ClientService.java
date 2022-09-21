package com.gorillaz.clients.service;

import java.util.List;

import com.gorillaz.clients.entity.Client;

public interface ClientService {
	public Long insertClient(Client client);
	public Client getClient(Long id);
	public List<Client> getClients();
	public Client updateClient(Client client, Long id);
	public Client deleteClient(Long id);


	
}
