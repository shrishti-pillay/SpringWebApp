package com.spring.project.services;

import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.spring.project.domain.Student;


@Service
public class EmailServiceImpl implements EmailService {
	private final JavaMailSender javaMailSender; 
	private final StudentService studentService;
	
	@Autowired
	public EmailServiceImpl(JavaMailSender javaMailSender, StudentService studentService) {
		this.javaMailSender = javaMailSender;
		this.studentService = studentService;
	}
	
	@Override
	public void sendEmailToStudent(Integer studentId, String subject, String content) {
		String studentEmail = studentService.getStudent(studentId).getEmail(); 
		MimeMessage emailMessage = null; 
		try {
			emailMessage = javaMailSender.createMimeMessage(); 
			emailMessage.setSubject(subject);
			emailMessage.setText(content);
			emailMessage.setRecipient(RecipientType.TO, new InternetAddress(studentEmail, true)); 
			// send out email 
			javaMailSender.send(emailMessage);
		}
		catch (AddressException e) {
			e.printStackTrace();
		}
		catch (MessagingException e) {
			e.printStackTrace();
		}
	}
		
		@Override
		public void sendEmailToAllStudents(String subject, String content) {
			for (Student s: studentService.getAllStudents()) {
			MimeMessage emailMessage = null; 
			try {
				emailMessage = javaMailSender.createMimeMessage(); 
				emailMessage.setSubject(subject);
				emailMessage.setText(content);
				emailMessage.setRecipient(RecipientType.TO, new InternetAddress(s.getEmail(), true));
				// send out email 
				javaMailSender.send(emailMessage);
			}
			catch (AddressException e) {
				e.printStackTrace();
			}
			catch (MessagingException e) {
				e.printStackTrace();
			}
		}
	}
		
}


