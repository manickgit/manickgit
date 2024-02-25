package com.manick.rest.webservices.restfulservices.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.manick.rest.webservices.restfulservices.entity.Name;
import com.manick.rest.webservices.restfulservices.entity.PersonV1;
import com.manick.rest.webservices.restfulservices.entity.PersonV2;

@RestController
public class VersioningPersonController {

	@GetMapping(path="/v1/person")
	public PersonV1 getFirstVersionPerson() {
		return new PersonV1("Manickaraj Murugesan");
	}
	
	@GetMapping(path="/v2/person")
	public PersonV2 getSecondVersionPerson() { 
		return new PersonV2(new Name("Manickaraj", "Murugesan"));
	}
	
	@GetMapping(path="/person", params = "version=1")
	public PersonV1 getFirstVersionPersonRequestParam() {
		return new PersonV1("Manickaraj Murugesan");
	}
	
	@GetMapping(path="/person", params = "version=2")
	public PersonV2 getSecondVersionPersonRequestParam() { 
		return new PersonV2(new Name("Manickaraj", "Murugesan"));
	}
	
	@GetMapping(path="/person/header", headers = "X-API-VERSION=1")
	public PersonV1 getFirstVersionPersonRequestHeader() {
		return new PersonV1("Manickaraj Murugesan");
	}
	
	@GetMapping(path="/person/header", headers = "X-API-VERSION=2")
	public PersonV2 getSecondVersionPersonRequestHeader() { 
		return new PersonV2(new Name("Manickaraj", "Murugesan"));
	}
	
	@GetMapping(path="/person/accept", produces = "application/vnd.company.app-v1+json")
	public PersonV1 getFirstVersionPersonAcceptHeader() {
		return new PersonV1("Manickaraj Murugesan");
	}
	
	@GetMapping(path="/person/accept", produces = "application/vnd.company.app-v2+json")
	public PersonV2 getSecondVersionPersonAcceptHeader() { 
		return new PersonV2(new Name("Manickaraj", "Murugesan"));
	}
}
