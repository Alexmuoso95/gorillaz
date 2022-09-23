package com.gorillaz.clients.service;

import java.util.List;

import com.gorillaz.clients.entity.Client;
import com.gorillaz.clients.request.ClientRequest;

public interface ClientService {
	public List<Client> insertTwentyRandomClients();
	public Long insertClient(ClientRequest client);
	public Client getClient(Long id);
	public List<Client> getClients();
	public Client updateClient(Client client, Long id);
	public Client deleteClient(Long id);
}
