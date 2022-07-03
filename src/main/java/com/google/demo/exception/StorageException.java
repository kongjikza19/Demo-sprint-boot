package com.google.demo.exception;

public class StorageException extends RuntimeException{

	public StorageException() {
		// TODO Auto-generated constructor stub
		super("Product Not Found");
	}
	
	public StorageException(String message) {
		// TODO Auto-generated constructor stub
		super(message);
	}
}
