package com.gorillaz.core.service;

import java.util.List;

import com.gorillaz.core.entity.Client;
import com.gorillaz.core.request.ClientRequest;

public interface ClientService {
	public List<Client> insertTwentyRandomClients();
	public Long createClient(ClientRequest client);
	public Client getClient(Long id);
	public List<Client> getClients();
	public Client updateClient(ClientRequest client, Long id);
	public void deleteClient(Long id);
	public void deleteAll();

}
