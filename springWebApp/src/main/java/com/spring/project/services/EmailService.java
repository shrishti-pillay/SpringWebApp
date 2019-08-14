package com.spring.project.services;

public interface EmailService {
	public void sendEmailToStudent(Integer studentId, String subject, String content);
	public void sendEmailToAllStudents(String subject, String content);

}
