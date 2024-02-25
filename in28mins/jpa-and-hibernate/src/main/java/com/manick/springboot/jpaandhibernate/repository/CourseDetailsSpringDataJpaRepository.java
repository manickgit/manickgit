package com.manick.springboot.jpaandhibernate.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.manick.springboot.jpaandhibernate.entity.CourseDetails;

public interface CourseDetailsSpringDataJpaRepository extends JpaRepository<CourseDetails, Long> {

	public CourseDetails findByAuthor(String author);
}
