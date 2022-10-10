package com.gorillaz.core.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.gorillaz.core.model.entity.Client;
import com.gorillaz.core.model.request.ClientRequest;

public interface ClientService {
	public List<Client> insertTwentyRandomClients();
	public Long createClient(ClientRequest client);
	
//	public boolean uploadFile(MultipartFile file , Long id);

	public Client getClient(Long id);
	public Page<Client> getClients(Pageable pageable);
	public List<Client> getClients();
	public Client updateClient(ClientRequest client, Long id);
	public void deleteClient(Long id);
	public void deleteAll();

}
