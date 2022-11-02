package com.gorillaz.core.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultActions;


@SpringBootTest
@AutoConfigureMockMvc
public class ClientControllerTest {

	private static final int VALID_CLIENT_ID = 100;
	private static final int INVALID_CLIENT_ID = 500;

	@Autowired
	private MockMvc mvcMock;
	
	@Test
	public void test_get_clientById_should_return_client_success() throws Exception {
		RequestBuilder request = get("/v1/clients/{id}",VALID_CLIENT_ID)
								  .content(getClientResponse_Success())
								  .accept(MediaType.APPLICATION_JSON);
		ResultActions result = mvcMock.perform(request);
		result.andExpect(status().isOk());
	}
	
	@Test
	public void test_get_clientById_should_return_404() throws Exception {
		RequestBuilder request = get("/v1/clients/{id}",INVALID_CLIENT_ID)
								  .accept(MediaType.APPLICATION_JSON);
		ResultActions result = mvcMock.perform(request);
		result.andExpect(status().isNotFound());
	}
	
	private String getClientResponse_Success() {
	return	"{\r\n"
		  		+ "    \"id\": 100,\r\n"
		  		+ "    \"name\": \"ALEX\",\r\n"
		  		+ "    \"lastName\": \"GUZMAN\",\r\n"
		  		+ "    \"email\": \"ALEXMUOSORIO@GMAIL.COM\",\r\n"
		  		+ "    \"phoneNumber\": \"0000\",\r\n"
		  		+ "    \"createAt\": null,\r\n"
		  		+ "    \"photo\": null,\r\n"
		  		+ "    \"addresses\": []\r\n"
		  		+ "}";
	}
}
