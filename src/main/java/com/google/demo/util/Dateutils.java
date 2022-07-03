package com.google.demo.util;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

//@Component
@Configuration
@Scope("prototype")
public class Dateutils {
	private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-YYYY");
	
	public Dateutils() {
		System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^");
	}
	

//	@Bean
	public String todayString() {
		return simpleDateFormat.format(new Date());
	}
	
	public String todayString1() {
		return simpleDateFormat.format(new Date());
	}
	
//	@PostConstruct
//	public void post() {
//		System.out.println("$$$$$$$$$$$$");
//	}
//	
//	@PreDestroy
//	public void pre() {
//		System.out.println("$$$$$$$$$$$$111");
//	}
}
