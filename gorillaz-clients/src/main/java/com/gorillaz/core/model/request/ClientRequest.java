package com.gorillaz.core.model.request;

import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.gorillaz.core.model.entity.Address;

import lombok.Data;

@Data
public class ClientRequest {
	
	@NotNull(message = "Nombre no puede estar vacio")
	@NotBlank(message = "Nombre no puede estar vacio")
	private String name;
	
	@NotNull(message = "Apellidos no puede estar vacio")
	@NotBlank(message = "Apellidos no puede estar vacio")
	private String lastName;

	@Email(message = "Email debe contener  @ y .com .mx etc..")
	private String email;

	@Pattern(regexp = "[0-9]+", message = "El Telefono solo acepta numeros")
	private String phoneNumber;
	
	private List<Address> addresses;
}
