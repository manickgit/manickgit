package com.manick.rest.webservices.restfulservices.controller;

import java.util.Locale;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.manick.rest.webservices.restfulservices.dto.HelloWorldBean;

@RestController
public class HelloWorldController {
	
	private MessageSource messageSource;
	
	public HelloWorldController(MessageSource messageSource) {
		this.messageSource = messageSource;
	}
	
	@GetMapping(path = "/hello-world")
	public String helloWorld() {
		return " Hello World!";
	}

	@GetMapping(path= "/hello-world-bean")
	public HelloWorldBean helloWorldBean() {
		return new HelloWorldBean("Hello World.");
	}
	
	@GetMapping(path="/hello-world/path-variable/{name}")
	public HelloWorldBean hellowWorldPathVar( @PathVariable String name) {
		return new HelloWorldBean(String.format("Hello! %s", name));
	}
	
	@GetMapping(path = "/hello-world-internationalized")
	public String helloWorldInternationalized() {
		
		Locale locale = LocaleContextHolder.getLocale();
		
		String[] names = {"manick"};
		return messageSource.getMessage("good.morning.message", names, "EN: GM",locale);
		
		//return " Hello World - V2!";
	}
}
