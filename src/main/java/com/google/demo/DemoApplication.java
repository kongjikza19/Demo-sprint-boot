package com.google.demo;

import java.util.Arrays;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import com.google.demo.service.StorageService;
import com.google.demo.util.Dateutils;

@SpringBootApplication
//@EnableAutoConfiguration
//@ComponentScan
//@RestController
public class DemoApplication {
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
		
	}

	@Bean
	CommandLineRunner run(ApplicationContext ctx, Dateutils dateutils,StorageService storageService) {
		
//		Dateutils dateutil1 = ctx.getBean(Dateutils.class);
//		Dateutils dateutil2 = ctx.getBean(Dateutils.class);
//		
//		
//		ctx.getAutowireCapableBeanFactory().destroyBean(dateutil1);
//		ctx.getAutowireCapableBeanFactory().destroyBean(dateutil2);
		return args -> {
//			String[] beans = ctx.getBeanDefinitionNames();
//			Arrays.sort(beans);
//			for (String bean : beans) {
//				System.out.println(bean);
//			}
//			
//			System.out.println("!!!!!!!!!! "+dateutils.todayString());
			storageService.init();
		};
	}
}