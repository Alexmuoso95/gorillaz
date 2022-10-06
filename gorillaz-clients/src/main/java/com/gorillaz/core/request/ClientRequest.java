package com.gorillaz.core.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import lombok.Data;

@Data
public class ClientRequest {
	
	@NotNull(message = "Nombre no puese estar vacio")
	@NotBlank(message = "Nombre no puese estar vacio")
	private String name;
	
	@NotNull(message = "Apellidos no puese estar vacio")
	@NotBlank(message = "Apellidos no puese estar vacio")
	private String lastName;

	@Email(message = "Email debe contener  @ y .com .mx etc..")
	private String email;

	@Pattern(regexp = "[0-9]+", message = "Telefono solo acepta numeros")
	private String phoneNumber;
}
