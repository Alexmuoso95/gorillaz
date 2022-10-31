package com.gorillaz.core.model.response;

import java.util.List;

import com.gorillaz.core.model.entity.Address;
import com.gorillaz.core.model.entity.Client;

import lombok.Data;

@Data
public class ClientsResponse{
	private Client client;
	private List<Address> address;
}
