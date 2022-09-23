package com.gorillaz.clients.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.gorillaz.clients.entity.Client;
import com.gorillaz.clients.request.ClientRequest;

@Component
public class ClientMapper {
	public Client mapClient(ClientRequest clientRequest) {
		Client client = new Client();
		client.setName(clientRequest.getName());
		client.setMiddleName(clientRequest.getMiddleName());
		client.setLastName(clientRequest.getLastName());
		return client;
	}
	public List<Client> mapClientRequest(List<ClientRequest> clientRequests) {
		List<Client> clients = new ArrayList<>();
		clientRequests.forEach(clientRequest -> {
			Client client = new Client();
			client.setName(clientRequest.getName());
			client.setMiddleName(clientRequest.getMiddleName());
			client.setLastName(clientRequest.getLastName());
			clients.add(client);
		});
		return clients;
	}
	
}
