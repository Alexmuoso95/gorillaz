package com.gorillaz.clients.request;

import lombok.Data;

@Data
public class JwtRequest {
	private String username;
	private String password;
}
