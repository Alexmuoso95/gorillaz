package com.gorillaz.core.exceptions;

import org.springframework.http.HttpStatus;

public class ErrorResponse {

	private String message;
	private HttpStatus code;
	private String moreInfo;

	public ErrorResponse(String message, HttpStatus code, String moreInfo) {
		this.message = message;
		this.code = code;
		this.moreInfo = moreInfo;
	}

	public String getMessage() {

		return message;
	}

	public HttpStatus getCode() {

		return code;
	}

	public String getMoreInfo() {

		return moreInfo;
	}
}