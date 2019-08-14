package com.spring.project.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import com.spring.project.domain.StudentCourse;
import com.spring.project.repositories.CourseRepository;
import com.spring.project.repositories.StudentCourseRepository;
import com.spring.project.repositories.StudentRepository;

@Service
public class StudentCourseServiceImpl implements StudentCourseService {
	private final StudentCourseRepository studentCourseRepository; 
	private final StudentRepository studentRepository; 
	private final CourseRepository courseRepository; 

	public StudentCourseServiceImpl(StudentCourseRepository studentCourseRepository, CourseRepository courseRepository, StudentRepository studentRepository) {
		this.studentCourseRepository = studentCourseRepository;
		this.courseRepository = courseRepository;
		this.studentRepository = studentRepository;
	}
	
	@Override
	public void addStudentCourse(Integer studentId, Integer courseId) {
		StudentCourse studentCourse = new StudentCourse();
		studentCourse.setCourse(courseRepository.findById(courseId).get());
		studentCourse.setStudent(studentRepository.findById(studentId).get());
		studentCourseRepository.save(studentCourse);
	}

	@Override
	public void deleteStudentCourse(Integer studentId, Integer courseId) {
		List <StudentCourse> studentCourseList = new ArrayList<StudentCourse>();
		studentCourseRepository.findAll().iterator().forEachRemaining(studentCourseList::add);
		for(StudentCourse s: studentCourseList) {
			if (s.getStudent().getStudentId() == studentId && s.getCourse().getCourseId() == courseId){
				
				studentCourseRepository.delete(s);
			}
			
		}
		
	}

}
