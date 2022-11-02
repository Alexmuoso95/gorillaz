package com.gorillaz.core.model.response;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.gorillaz.core.model.entity.Address;
import com.gorillaz.core.model.entity.Invoice;

import lombok.Data;

@Data
public class ClientResponse {
	private Long id;
	private String name;
	private String lastName;
	private String email;
	private String phoneNumber;
	private Date createAt;
	private String photo;
	private List<Address> addresses = new ArrayList<>();
	private List<Invoice> invoices = new ArrayList<>();

}
