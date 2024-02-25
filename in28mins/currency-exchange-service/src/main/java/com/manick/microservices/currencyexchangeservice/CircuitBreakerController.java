package com.manick.microservices.currencyexchangeservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;

@RestController
public class CircuitBreakerController {

	private Logger logger = LoggerFactory.getLogger(CircuitBreakerController.class);

	@GetMapping(path = "sample-api")
	@Retry(name = "sample-api", fallbackMethod = "hardcodedResponse")
	public String retryApi() {

		logger.info("Sample API call received");
		ResponseEntity<String> entity = new RestTemplate().getForEntity("http://localhost:8000/dummy-api",
				String.class);

		return entity.getBody();
	}

	@GetMapping(path = "cb-sample-api")
	@CircuitBreaker(name = "default", fallbackMethod = "hardcodedResponse")
	public String cbApi() {

		logger.info("Sample API call received");
		ResponseEntity<String> entity = new RestTemplate().getForEntity("http://localhost:8000/dummy-api",
				String.class);

		return entity.getBody();
	}

	@GetMapping(path = "ratelimiter-sample-api")
	@RateLimiter(name = "default") // 2 calls per 10s
	public String retryCBApi() {

		logger.info("Ratelimiter:: Sample API call received");
		
		return "Sample API";
	}
	
	@GetMapping(path = "bulkhead-sample-api")
	@Bulkhead(name = "bh-sample-api") // Max 10 concurrent calls
	public String bulkHeadApi() {

		logger.info("Bulkhead:: Sample API call received");
		
		return "Sample API";
	}

	public String hardcodedResponse(Exception e) {
		return "Fallback response";
	}

}
