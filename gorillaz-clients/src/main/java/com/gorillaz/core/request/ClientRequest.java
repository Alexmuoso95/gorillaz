package com.gorillaz.core.request;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class ClientRequest {
	@NotBlank
	private String name;
	@NotBlank
	private String middleName;
	@NotBlank
	private String lastName;
}
