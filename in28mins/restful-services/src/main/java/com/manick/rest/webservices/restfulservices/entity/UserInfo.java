package com.manick.rest.webservices.restfulservices.entity;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;

@Entity(name = "user_info")
public class UserInfo {
	
	@Id
	private Long id;

	@Size(min = 2, message = "Name should have at least 2 chars & 5 max")
	@JsonProperty("user_name")
	private String name;
	
	@Past(message = "Birth date should be a past value")
	@JsonProperty("birth_date")
	private LocalDate birthDate;

	@OneToMany(mappedBy = "userInfo")
	@JsonIgnore
	private List<Post> posts;

	protected UserInfo() {
		
	}
	
	public UserInfo(Long id, String name, LocalDate birthDate) {
		super();
		this.id = id;
		this.name = name;
		this.birthDate = birthDate;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocalDate getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(LocalDate birthDate) {
		this.birthDate = birthDate;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", birthDate=" + birthDate + "]";
	}

	public List<Post> getPosts() {
		return posts;
	}

	public void setPosts(List<Post> posts) {
		this.posts = posts;
	}
	
	
}
