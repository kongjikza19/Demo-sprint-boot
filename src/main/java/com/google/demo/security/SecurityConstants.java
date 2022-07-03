package com.google.demo.security;

public interface SecurityConstants {
	String SECRET_KEY = "KongKong";
	String TOKEN_PREFIX = "Bearer ";
	String HEADER_AUTHORIZATION = "Authorization";
	String CLAIMS_ROLE = "role";
	long EXPIRATION_TIME = (1 * 60 * 1000);
}
