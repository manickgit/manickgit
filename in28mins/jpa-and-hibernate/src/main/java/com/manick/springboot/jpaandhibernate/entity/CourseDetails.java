package com.manick.springboot.jpaandhibernate.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity(name = "course_details")
public class CourseDetails {

	@Id
	private long id;

	private String name;

	private String author;

	public CourseDetails() {

	}

	public CourseDetails(long id, String name, String author) {
		super();
		this.id = id;
		this.name = name;
		this.author = author;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	@Override
	public String toString() {
		return "Course [id=" + id + ", name=" + name + ", author=" + author + "]";
	}

}
