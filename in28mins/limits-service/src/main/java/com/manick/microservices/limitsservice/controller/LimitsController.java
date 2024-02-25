package com.manick.microservices.limitsservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.manick.microservices.limitsservice.configuration.Configuration;
import com.manick.microservices.limitsservice.controller.bean.Limits;

@RestController
public class LimitsController {

	@Autowired
	private Configuration configuration;

	@GetMapping(path = "/limits")
	public Limits retrieveLimits() {
		return new Limits(configuration.getMinimum(), configuration.getMaximum());
		//return new Limits(1, 1000);
	}
}
