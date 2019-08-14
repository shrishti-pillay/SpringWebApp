package com.spring.project.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class StudentCourse {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer studentCourseId;
 
    @ManyToOne
    @JoinColumn(name = "student_id")
    Student student;
 
    @ManyToOne
    @JoinColumn(name = "course_id")
    Course course;

	public StudentCourse(Student student, Course course) {
		this.student = student;
		this.course = course;
	} 
	
	public StudentCourse() {
	
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}
    public Integer getStudentCourseId() {
    	return studentCourseId; 
    }
    
    public void setStudentCourseId(Integer studentCourseId) {
    	this.studentCourseId = studentCourseId; 
    }
	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	@Override
	public String toString() {
		return "StudentCourse [student=" + student + ", course=" + course + "]";
	}

	
}
