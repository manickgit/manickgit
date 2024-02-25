package com.manick.microservices.currencyconversionservice.proxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.manick.microservices.currencyconversionservice.bean.CurrencyConversion;

//@FeignClient(name="currency-exchange", url = "localhost:8000")

// do the below post registering with Eureka naming server
@FeignClient(name="currency-exchange")
public interface CurrencyExchangeProxy {
	
	@GetMapping(path="/currency-exchange/from/{from}/to/{to}")
	public CurrencyConversion retrieveExchangeValue(
			@PathVariable String from,
			@PathVariable String to);
		
}
