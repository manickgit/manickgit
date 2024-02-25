package com.manick.rest.webservices.restfulservices.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties({"user_password","birthDate"}) 
public class FilterBeanExample {

	private String userName;
	
	//@JsonIgnore
	@JsonProperty("user_password")
	private String password;
	
	private LocalDateTime birthDate;
	
	@JsonIgnore
	private int age;
	
	private boolean active;
	
	public FilterBeanExample(String userName, String password, LocalDateTime birthDate, int age, boolean active) {
		super();
		this.userName = userName;
		this.password = password;
		this.birthDate = birthDate;
		this.age = age;
		this.active = active;
	}

	public String getUserName() {
		return userName;
	}

	public String getPassword() {
		return password;
	}

	public LocalDateTime getBirthDate() {
		return birthDate;
	}

	public int getAge() {
		return age;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	@Override
	public String toString() {
		return "FilterBeanExample [userName=" + userName + ", password=" + password + ", birthDate=" + birthDate
				+ ", age=" + age + "]";
	}
	
	
	
}
