package com.example.demo;

public class TenantNotResolvedException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public TenantNotResolvedException() {
		super();
	}

	public TenantNotResolvedException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public TenantNotResolvedException(String message, Throwable cause) {
		super(message, cause);
	}

	public TenantNotResolvedException(String message) {
		super(message);
	}

	public TenantNotResolvedException(Throwable cause) {
		super(cause);
	}
	
	

}
