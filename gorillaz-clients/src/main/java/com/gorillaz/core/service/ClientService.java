package com.gorillaz.core.service;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.gorillaz.core.enums.ExpandEnum;
import com.gorillaz.core.model.entity.Client;
import com.gorillaz.core.model.request.ClientRequest;
import com.gorillaz.core.model.response.ClientResponse;

public interface ClientService {
	public Long createClient(ClientRequest client);
	
//	public boolean uploadFile(MultipartFile file , Long id);

	public ClientResponse getClient(Long clienId,List<ExpandEnum> expands) throws EntityNotFoundException;
	public Page<Client> getClients(Pageable pageable);
	public List<Client> getClients();
	public ClientResponse updateClientAndAddresses(ClientRequest client, Long clienId);
	public void deleteClient(Long clienId);
	public void deleteAll();
	String createExcel(Long clientId);
	
	public ClientResponse getClientsWithAddresses(Long clientId);
}
