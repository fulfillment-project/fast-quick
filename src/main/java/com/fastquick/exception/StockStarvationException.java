package com.fastquick.exception;

public class StockStarvationException extends RuntimeException {
	public StockStarvationException() {

	}

	public StockStarvationException(String message) {
		super(message);
	}

	public StockStarvationException(String message, Throwable cause) {
		super(message, cause);
	}

}
