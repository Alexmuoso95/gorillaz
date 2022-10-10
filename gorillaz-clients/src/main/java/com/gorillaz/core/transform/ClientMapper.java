package com.gorillaz.core.transform;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.gorillaz.core.model.entity.Client;
import com.gorillaz.core.model.request.ClientRequest;

@Component
public class ClientMapper {
	
	public Client mapClientRequest(ClientRequest clientRequest) {
		Client client = new Client();
		client.setName(clientRequest.getName());
		client.setLastName(clientRequest.getLastName());
		client.setEmail(clientRequest.getEmail());
		client.setPhoneNumber(clientRequest.getPhoneNumber());
		return client;
	}
	public List<Client> mapClientRequest(List<ClientRequest> clientRequests) {
		List<Client> clients = new ArrayList<>();
		clientRequests.forEach(clientRequest -> {
			Client client = new Client();
			client.setName(clientRequest.getName());
			client.setLastName(clientRequest.getLastName());
			client.setEmail(clientRequest.getEmail());
			client.setPhoneNumber(clientRequest.getPhoneNumber());
			clients.add(client);
		});
		return clients;
	}
	
}
