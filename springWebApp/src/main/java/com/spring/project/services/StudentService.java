package com.spring.project.services;

import java.util.List;

import com.spring.project.domain.Student;

public interface StudentService {
	
	public void addOrUpdateStudent(Student student); 
	public Student getStudent(Integer studentId);
	public void deleteStudent(Integer studentId);
	public List<Student> getAllStudents(); 
}
