package com.manick.rest.webservices.restfulservices.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SpringSecurityConfiguration {

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity security) throws Exception {
		
		// Force all requests to be authenticated
		security.authorizeHttpRequests( 
				auth -> auth.anyRequest().authenticated()
		);
		
		// show a web page if a request is authenticated
		security.httpBasic(Customizer.withDefaults());
		
		// FOr POST & PUT
		security.csrf().disable() ;
		return security.build();
	}
}
