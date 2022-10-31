package com.gorillaz.core.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.gorillaz.core.enums.ExpandEnum;
import com.gorillaz.core.model.entity.Client;
import com.gorillaz.core.model.request.ClientRequest;
import com.gorillaz.core.model.response.ClientResponse;

public interface ClientService {
	public List<Client> insertTwentyRandomClients();
	public Long createClient(ClientRequest client);
	
//	public boolean uploadFile(MultipartFile file , Long id);

	public ClientResponse getClient(Long id,List<ExpandEnum> expands);
	public Page<Client> getClients(Pageable pageable);
	public List<Client> getClients();
	public Client updateClient(ClientRequest client, Long id);
	public void deleteClient(Long id);
	public void deleteAll();
	
	
	public ClientResponse getClientsWithAddresses(Long clientId);
}
