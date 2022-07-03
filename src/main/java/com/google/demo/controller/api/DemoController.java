package com.google.demo.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.demo.util.Dateutils;

//import com.google.demo.util.Dateutils;

@RestController
public class DemoController {

//	@Autowired
//	Dateutils dateutils;
	private SayService sayService;

//	public DemoController(Dateutils dateutils, @Qualifier("SayServiceImpl_test") SayService sayService) {
//		this.dateutils = dateutils;
//		this.sayService = sayService;
//	}

	@GetMapping("/")
	public String getToday(Dateutils dateutils) {
//		this(null);
		return dateutils.todayString1() + "11112121";
	}

	@GetMapping("/say")
	public String getSay() {
//		this(null);
		return sayService.say();
	}
}

interface SayService {
	String say();
}

@Component("SayServiceImpl_test")
class SayServiceImpl implements SayService {

	@Override
	public String say() {
		return "Test";
	}

}

@Component
class SayServiceImpl1 implements SayService {

	@Override
	public String say() {
		return "Test111";
	}

}
