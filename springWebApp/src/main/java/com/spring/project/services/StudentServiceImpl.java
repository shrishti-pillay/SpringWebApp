package com.spring.project.services;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.spring.project.domain.Student;
import com.spring.project.repositories.CourseRepository;
import com.spring.project.repositories.StudentRepository;

@Service
public class StudentServiceImpl implements StudentService {
	private final StudentRepository studentRepository;  
	
	@Autowired
	public StudentServiceImpl(StudentRepository studentRepository){
		this.studentRepository = studentRepository;  
	}
	@Override
	public void addOrUpdateStudent(Student student) {
		studentRepository.save(student);
		
	}

	@Override
	public Student getStudent(Integer studentId) {
		Student student = studentRepository.findById(studentId).get();
		return student;
	}

	@Override
	public void deleteStudent(Integer studentId) {
		studentRepository.deleteById(studentId);
		
	}

	@Override
	public List<Student> getAllStudents() {
		List<Student> studentList = new ArrayList<Student>(); 
		studentRepository.findAll().iterator().forEachRemaining(studentList::add); // lambda expression
		return studentList;
	}


}
