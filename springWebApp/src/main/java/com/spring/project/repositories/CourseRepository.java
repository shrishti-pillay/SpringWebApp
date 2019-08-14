package com.spring.project.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.project.domain.Course;

public interface CourseRepository extends JpaRepository<Course, Integer> {
	
	public Course findByCourseName(String courseName);
	public List<Course> findByCreditsAndDuration(Integer credits, Integer duration);

}
