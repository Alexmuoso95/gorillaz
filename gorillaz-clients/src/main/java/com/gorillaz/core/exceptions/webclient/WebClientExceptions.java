package com.gorillaz.core.exceptions.webclient;

public class WebClientExceptions extends RuntimeException{
	private static final long serialVersionUID = 4990670438152994719L;
	public WebClientExceptions() {	}
	public WebClientExceptions(String message) { super(message);	}
	public WebClientExceptions(String message, Throwable cause) { super(message,cause);	}
	public WebClientExceptions(Throwable cause) { super(cause);	}
}
