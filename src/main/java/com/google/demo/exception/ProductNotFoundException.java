package com.google.demo.exception;

public class ProductNotFoundException extends RuntimeException{

	public ProductNotFoundException() {
		// TODO Auto-generated constructor stub
		super("Product Not Found");
	}
	
	public ProductNotFoundException(long id) {
		// TODO Auto-generated constructor stub
		super("Product Not Found " + id);
	}

	public ProductNotFoundException(String name) {
		// TODO Auto-generated constructor stub
		super("Product Not Found " + name);
	}
}
