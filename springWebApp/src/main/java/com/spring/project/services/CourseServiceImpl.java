package com.spring.project.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.project.domain.Course;
import com.spring.project.repositories.CourseRepository;

@Service
public class CourseServiceImpl implements CourseService{
	private final CourseRepository courseRepository; 

	@Autowired
	public CourseServiceImpl(CourseRepository courseRepository) {
		this.courseRepository = courseRepository;
	}
	
	@Override
	public void addOrUpdateCourse(Course course) {
		courseRepository.save(course);
		
	}

	@Override
	public Course getCourse(Integer courseId) {
		Course course = courseRepository.findById(courseId).get(); // get() is to Retrieve course without optional wrapping 
		return course;
	}

	@Override
	public void deleteCourse(Integer courseId) {
		Course course = courseRepository.findById(courseId).get(); 
		if(course != null) {
			courseRepository.delete(course); 
		}
		// OR courseRepository.deleteById(courseId);
		
	}

	@Override
	public List<Course> getAllCourses() {
		List<Course> courseList = new ArrayList<Course>(); 
		courseRepository.findAll().iterator().forEachRemaining(courseList::add); // lambda expression
		return courseList;
	}

	@Override
	public Course findByCourseName(String courseName) {
		
		return courseRepository.findByCourseName(courseName);
	}

	@Override
	public List<Course> findByCreditsAndDuration(Integer credits, Integer duration) {
		
		return courseRepository.findByCreditsAndDuration(credits, duration);
	}

	

}
