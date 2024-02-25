package com.manick.springboot.jpaandhibernate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.manick.springboot.jpaandhibernate.entity.Course;
import com.manick.springboot.jpaandhibernate.entity.CourseDetails;
import com.manick.springboot.jpaandhibernate.repository.CourseDetailsJpaRepository;
import com.manick.springboot.jpaandhibernate.repository.CourseDetailsSpringDataJpaRepository;
import com.manick.springboot.jpaandhibernate.repository.CourseJdbcRepository;

@Component
public class CourseCommandLineRunner implements CommandLineRunner {

	@Autowired
	private CourseJdbcRepository repository;
	
	@Autowired
	private CourseDetailsJpaRepository courseDetailsJpaRepository;
	
	@Autowired
	private CourseDetailsSpringDataJpaRepository courseDetailsSpringDataJpaRepository;
	
	@Override
	public void run(String... args) throws Exception {
		repository.insert(new Course(1, "Learn AWS Fast", "Manick"));
		repository.insert(new Course(2, "Learn GCP ", "Raj"));
		repository.insert(new Course(3, "Learn Azure", "Adam"));
		
		repository.deleteById(1);
		
		System.out.println(repository.findById(3));
		
		courseDetailsJpaRepository.insert(new CourseDetails(1, "Learn AWS Fast", "Manick") );
		courseDetailsJpaRepository.insert(new CourseDetails(2, "Learn GCP Fast", "Adam") );
		courseDetailsJpaRepository.insert(new CourseDetails(3, "Learn Azure Fast", "Shoba") );
		
		courseDetailsJpaRepository.deleteById(2);
		
		System.out.println(courseDetailsJpaRepository.findById(3));
		
		
		courseDetailsSpringDataJpaRepository.save(new CourseDetails(4, "Learn AWS Fast", "Mark") );
		courseDetailsSpringDataJpaRepository.save(new CourseDetails(5, "Learn GCP Fast", "Freddy") );
		courseDetailsSpringDataJpaRepository.save(new CourseDetails(6, "Learn Azure Fast", "Jacon") );
		
		courseDetailsSpringDataJpaRepository.deleteById(5L);
		
		System.out.println(courseDetailsSpringDataJpaRepository.findById(6L));
		
		System.out.println(courseDetailsSpringDataJpaRepository.findByAuthor("Jacon"));
	}
}
