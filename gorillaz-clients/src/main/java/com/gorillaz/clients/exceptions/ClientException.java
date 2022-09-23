package com.gorillaz.clients.exceptions;

public class ClientException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	public ClientException(String message) {
		super(message);
	};
	public ClientException(String message, Throwable cause) {
		super(message);
	};
	public ClientException(Throwable cause) {
		super(cause);
	};
}
