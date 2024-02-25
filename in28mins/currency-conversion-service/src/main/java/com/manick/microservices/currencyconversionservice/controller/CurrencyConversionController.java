package com.manick.microservices.currencyconversionservice.controller;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.manick.microservices.currencyconversionservice.bean.CurrencyConversion;
import com.manick.microservices.currencyconversionservice.proxy.CurrencyExchangeProxy;

@Configuration(proxyBeanMethods = false)
class RestTemplateConfiguration {
    
    @Bean
    RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }
}

@RestController
public class CurrencyConversionController {
	
	@Autowired
	private CurrencyExchangeProxy currencyExchangeProxy;
	
	@Autowired
	private RestTemplate restTemplate;

	@RequestMapping(path="/currency-conversion/from/{from}/to/{to}/quantity/{qty}")
	public CurrencyConversion calcualteConversion(
			@PathVariable String from,
			@PathVariable String to,
			@PathVariable BigDecimal qty) {
		
		Map<String, String> uriVariable = new HashMap<>();
		uriVariable.put("from", from);
		uriVariable.put("to", to);
		
		ResponseEntity<CurrencyConversion> entity = restTemplate.getForEntity(
				"http://localhost:8000/currency-exchange/from/{from}/to/{to}", 
				CurrencyConversion.class, 
				uriVariable);
		CurrencyConversion currencyConversion = entity.getBody();
		
		return new CurrencyConversion(currencyConversion.getId(), 
				from, 
				to, 
				currencyConversion.getConversionMultiple(), 
				qty, 
				qty.multiply(currencyConversion.getConversionMultiple()), 
				currencyConversion.getEnvironment() + " "+ "rest template");
		
	}
	
	@RequestMapping(path="/currency-conversion-feign/from/{from}/to/{to}/quantity/{qty}")
	public CurrencyConversion calcualteConversionFeign(
			@PathVariable String from,
			@PathVariable String to,
			@PathVariable BigDecimal qty) {
		
		CurrencyConversion currencyConversion = currencyExchangeProxy.retrieveExchangeValue(from, to);
		
		return new CurrencyConversion(currencyConversion.getId(), 
				from, 
				to, 
				currencyConversion.getConversionMultiple(), 
				qty, 
				qty.multiply(currencyConversion.getConversionMultiple()), 
				currencyConversion.getEnvironment() + " "+ "Feign");
		
	}
	
}
