package com.gorillaz.core.transform;

import java.math.BigInteger;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.gorillaz.core.entity.Client;
import com.gorillaz.core.request.ClientRequest;

@Component
public class ClientMapper {
	
	DateTimeFormatter dtf = DateTimeFormatter.ofPattern("uuuu-MM-dd");
	LocalDate localDate = LocalDate.now();
	
	public Client mapClientRequest(ClientRequest clientRequest) {
		Client client = new Client();
		client.setName(clientRequest.getName());
		client.setLastName(clientRequest.getLastName());
		client.setEmail(clientRequest.getEmail());
		client.setPhoneNumber(new BigInteger(clientRequest.getPhoneNumber()) );
		client.setCreateAt(dtf.format(localDate));
		return client;
	}
	public List<Client> mapClientRequest(List<ClientRequest> clientRequests) {
		List<Client> clients = new ArrayList<>();
		clientRequests.forEach(clientRequest -> {
			Client client = new Client();
			client.setName(clientRequest.getName());
			client.setLastName(clientRequest.getLastName());
			client.setEmail(clientRequest.getEmail());
			client.setPhoneNumber(new BigInteger(clientRequest.getPhoneNumber()));
			client.setCreateAt(dtf.format(localDate));
			clients.add(client);
		});
		return clients;
	}
	
}
