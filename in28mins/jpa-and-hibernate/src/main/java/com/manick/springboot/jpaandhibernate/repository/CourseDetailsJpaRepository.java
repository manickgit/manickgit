package com.manick.springboot.jpaandhibernate.repository;

import org.springframework.stereotype.Repository;

import com.manick.springboot.jpaandhibernate.entity.CourseDetails;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@Repository
@Transactional
public class CourseDetailsJpaRepository {

	@PersistenceContext
	private EntityManager entityManager;
	
	public void insert(CourseDetails course) {
		entityManager.merge(course);
	}
	
	public CourseDetails findById(long id) {
		return entityManager.find(CourseDetails.class, id);
	}
	
	public void deleteById(long id) {
		CourseDetails course = entityManager.find(CourseDetails.class, id);
		entityManager.remove(course);
	}
}
