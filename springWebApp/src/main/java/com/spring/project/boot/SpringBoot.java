package com.spring.project.boot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.spring.project.domain.Course;
import com.spring.project.domain.CourseLevel;
import com.spring.project.domain.Student;
import com.spring.project.repositories.CourseRepository;
import com.spring.project.repositories.StudentRepository;

//@Component // to create an object of this class 
public class SpringBoot implements ApplicationListener<ContextRefreshedEvent> {
	private CourseRepository courseRepository; 
	private StudentRepository studentRepository; 
	
	@Autowired 
	public SpringBoot(StudentRepository studentRepository, CourseRepository courseRepository) {
		this.studentRepository = studentRepository;
		this.courseRepository = courseRepository;
	}

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		init(); 
		
	}
	private void init() {
		Course c1 = new Course("Java Programming", 5, 2, "This course provides a basic knowledge of the Java language, the data types and the OOPS concepts.", CourseLevel.BASIC);
		Course c2 = new Course("Python Data Analytics", 10, 3, "This course provides an intermediate understanding of the python packages such as numpy and matplotlib.", CourseLevel.INTERMEDIATE);
		Course c3 = new Course("C Programming", 6, 1, "This course provides a basic understanding of C, the language syntax and data types.", CourseLevel.BASIC);
		Student s1 = new Student("Shrishti Pillay","shrishtipillay@gmail.com", "83459111" );
		Student s2 = new Student("Jane Myers","jmyres@gmail.com", "81119111" );
		Student s3 = new Student("Jacob Regers","jregers@gmail.com", "82349111" );
		studentRepository.save(s1); 
		studentRepository.save(s2); 
		studentRepository.save(s3); 
		courseRepository.save(c1);
		courseRepository.save(c2);
		courseRepository.save(c3);
	}
	

}
