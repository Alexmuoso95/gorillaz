package com.gorillaz.core.transform;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.gorillaz.core.model.entity.Address;
import com.gorillaz.core.model.entity.Client;
import com.gorillaz.core.model.entity.Invoice;
import com.gorillaz.core.model.request.ClientRequest;
import com.gorillaz.core.model.response.ClientResponse;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class ClientMapper {
	
	public void mapClientDetails(ClientResponse clientResponse, Client client) {
		log.info("Mapping client Details : [{}]",client.getId());
		clientResponse.setId(client.getId());
		clientResponse.setName(client.getName());
		clientResponse.setLastName(client.getLastName());
		clientResponse.setPhoneNumber(client.getPhoneNumber());
		clientResponse.setEmail(client.getEmail());
	}
	
	public void mapClientAddresses(ClientResponse clientResponse, List<Address> adresseses) {
		log.info("Mapping Address ClientId : [{}]",clientResponse.getId());
		clientResponse.setAddresses(adresseses);
	}
	
	public void mapClientInvoices(ClientResponse clientResponse, List<Invoice> invoices) {
		log.info("Mapping Invoices ClientId [{}]",clientResponse.getId());
		clientResponse.setInvoices(invoices);
	}
	
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
