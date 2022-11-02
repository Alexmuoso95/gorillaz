package com.gorillaz.core.exceptions.db;


public class DBExceptions extends RuntimeException{
	private static final long serialVersionUID = 4990670438152994719L;
	public DBExceptions() {	}
	public DBExceptions(String message) { super(message);	}
	public DBExceptions(String message, Throwable cause) { super(message,cause);	}
	public DBExceptions(Throwable cause) { super(cause);	}
}
