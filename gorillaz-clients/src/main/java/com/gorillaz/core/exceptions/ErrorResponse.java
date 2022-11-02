package com.gorillaz.core.exceptions;

public class ErrorResponse {

	private String message;
	private Integer code;
	private String moreInfo;

	public ErrorResponse(String message, Integer code, String moreInfo) {
		this.message = message;
		this.code = code;
		this.moreInfo = moreInfo;
	}

	public String getMessage() {

		return message;
	}

	public Integer getCode() {

		return code;
	}

	public String getMoreInfo() {

		return moreInfo;
	}
}