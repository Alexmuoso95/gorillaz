package com.gorillaz.core.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.gorillaz.core.enums.ExpandEnum;
import com.gorillaz.core.exceptions.db.DBExceptions;
import com.gorillaz.core.exceptions.webclient.WebClientExceptions;
import com.gorillaz.core.model.entity.Address;
import com.gorillaz.core.model.entity.Client;
import com.gorillaz.core.model.entity.Invoice;
import com.gorillaz.core.model.request.ClientRequest;
import com.gorillaz.core.model.response.ClientResponse;
import com.gorillaz.core.repository.AddressDAO;
import com.gorillaz.core.repository.ClientDAO;
import com.gorillaz.core.repository.InvoiceDAO;
import com.gorillaz.core.service.ClientService;
import com.gorillaz.core.transform.ClientMapper;
import com.gorillaz.core.web.WebClientService;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@Service
@EnableAsync
public class ClientServiceImpl implements ClientService {

	@Autowired
	private ClientDAO clientDao;

	@Autowired
	private AddressDAO addressDao;
	
	@Autowired
	private InvoiceDAO invoiceDao;
	
	@Autowired
	private ClientMapper clientMapper;

	@Autowired
	WebClientService webClientService;
	
	@Override
	public Long createClient(ClientRequest clientRequest) {
		return clientDao.save(clientMapper.mapClientRequest(clientRequest)).getId();
	}

	@Override
	public ClientResponse getClient(Long clientId, List<ExpandEnum> expands) throws DBExceptions,WebClientExceptions{
		ClientResponse clientResponse = new ClientResponse();
		Client client = clientDao.findById(clientId).orElseThrow(() -> new DBExceptions("Client id : " + clientId + " doesn't exist"));
		log.info(":: GC - Client Id : {} found ", clientId);
		clientMapper.mapClientDetails(clientResponse, client);
		if(expands.contains(ExpandEnum.CLIENT_ADDRESSES)) {
			List<Address> addresses = addressDao.findAllByClientId(clientId); 
			clientResponse.setAddresses(addresses);
			clientMapper.mapClientAddresses(clientResponse, addresses);
		}
		if(expands.contains(ExpandEnum.CLIENT_INVOICES)) {
			List<Invoice> invoices = invoiceDao.findAllByClientId(clientId);
			clientMapper.mapClientInvoices(clientResponse, invoices);
		}
		clientResponse.setName(webClientService.getClientsEncryperCall(client).get(0).getName());
		return clientResponse;
	}

	@Override
	public List<Client> getClients() {
		return clientDao.findAll();
	}

	@Override
	public Page<Client> getClients(Pageable pageable) {
		return clientDao.findAll(pageable);
	}

	@Transactional
	@Override
	public ClientResponse updateClientAndAddresses(ClientRequest clientRequest, Long clientId) throws DBExceptions {
		ClientResponse clientResponse = new ClientResponse();
		try {
			Client client = clientDao.findById(clientId).orElseThrow(() -> new DBExceptions("Client id : " + clientId + " doesn't exist."));
			client.setName(clientRequest.getName());
			client.setLastName(clientRequest.getLastName());
			client.setEmail(clientRequest.getEmail());
			client.setPhoneNumber(clientRequest.getPhoneNumber());
			clientDao.save(client);
			log.info(":: UC - Clien ID  : {} Updated.", clientId);
			addressDao.deleteByClientId(clientId);
			log.info(":: UC - Addresses Removed for clientId : [{}] ", clientId );
			if((clientRequest.getAddresses() != null ) && (clientRequest.getAddresses().size() > 0)) {
				List<Address> addressesDTO = clientRequest.getAddresses().stream().map(address -> { 
					address.setClient(client); return address; 
				}).collect(Collectors.toList());
				addressDao.saveAll(addressesDTO);
				log.info(":: UC - Address saved for clientId : [{}]", clientId );
				clientMapper.mapClientAddresses(clientResponse, addressesDTO);
			}
		}catch (RuntimeException  e) {
			throw new DBExceptions(e.getMessage());
		}
		return clientResponse;
	}

	@Override
	public void deleteClient(Long id) {
		Client client =  clientDao.findById(id).orElse(null);
		if(client!=null) {
			clientDao.delete(client);
		}
	}
	
	@Async
	@Override
	public void deleteAll() {
		clientDao.deleteAll();
	}

	@Override
	public ClientResponse getClientsWithAddresses(Long clientId) 
	{
		Client client = clientDao.findById(clientId).orElse(null);
		ClientResponse clientResponse = new ClientResponse();
		clientResponse.setId(client.getId());
		clientResponse.setName(client.getName());
		clientResponse.setLastName(client.getLastName());
		clientResponse.setPhoneNumber(client.getPhoneNumber());
		clientResponse.setEmail(client.getEmail());
		List<Address> addresses = addressDao.findAllByClientId(clientId); 
		clientResponse.setAddresses(addresses);
		return clientResponse;
	}

	@Override
	public String createExcel(Long clientId) {
		
		return null;
	}

//	@Override
//	public boolean uploadFile(MultipartFile file, Long id) {
//		if(!file.isEmpty(file)) {
//			Client client = clientDao.findById(id).orElse(null);
//			if(client!=null) {
//				
//			}	
//		}else
//		return false;
//	}

}
