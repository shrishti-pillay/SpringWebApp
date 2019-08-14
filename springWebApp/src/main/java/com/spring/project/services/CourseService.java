package com.spring.project.services;

import java.util.List;

import com.spring.project.domain.Course;

public interface CourseService {
	
	public void addOrUpdateCourse(Course course); 
	public Course getCourse(Integer courseId);
	public void deleteCourse(Integer courseId);
	public List<Course> getAllCourses();
	public Course findByCourseName(String courseName); 
	public List<Course> findByCreditsAndDuration(Integer credits, Integer duration); 


}
