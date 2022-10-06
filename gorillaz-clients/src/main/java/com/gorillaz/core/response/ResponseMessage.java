package com.gorillaz.core.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ResponseMessage {
	private String message;
	private int status;
}
