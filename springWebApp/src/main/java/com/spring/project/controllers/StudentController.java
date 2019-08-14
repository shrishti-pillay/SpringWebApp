package com.spring.project.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.spring.project.domain.Course;
import com.spring.project.domain.Student;
import com.spring.project.domain.StudentCourse;
import com.spring.project.repositories.StudentCourseRepository;
import com.spring.project.services.CourseService;
import com.spring.project.services.EmailService;
import com.spring.project.services.StudentCourseService;
import com.spring.project.services.StudentService;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

@Controller
public class StudentController {
	
	private final StudentService studentService;
	private final EmailService emailService;
	
	@Autowired
	public StudentController(StudentService studentService, EmailService emailService) {
		this.studentService = studentService;
		this.emailService = emailService;
	
	}	
	
	//students
	
	@GetMapping({"", "/", "/home"}) // url mappings 
	public String showHomePage() {
		return "index"; // file or folder/file name 
	}
	
	@GetMapping("/viewstudents")
	public String showStudentListing(Model model)
	{
		Map<Student,String> studentProfiles = new LinkedHashMap<Student, String>();  
		for(Student s: studentService.getAllStudents()) {
			if(s.getProfilePic() == null) {
				studentProfiles.put(s, "");
			}
			else {
				studentProfiles.put(s, Base64.getEncoder().encodeToString(s.getProfilePic())); 
			}
		}
		List<Student> studentList = studentService.getAllStudents(); 
		//model.addAttribute("studentList", studentList); // key- value pair
		model.addAttribute("studentProfiles", studentProfiles);
		System.out.println("Navigating to student listing page.");
		return "students/studentlisting";
	}
	
	@GetMapping("/studentform")
	public String showStudentForm(Model model) {
		model.addAttribute("student", new Student()); // send empty object to add new course values 
		return "students/studentform";
	}
		
	@GetMapping("/student/{studentId}/edit") // path variable
	public String editStudentById(@PathVariable("studentId") Integer studentId, Model model) {
		model.addAttribute("student", studentService.getStudent(studentId));
		return "students/studentform"; 
	}
	
	@GetMapping("/student/{studentId}/delete") // path variable
	public String deleteStudentById(@PathVariable("studentId") Integer studentId) {
		 studentService.deleteStudent(studentId);
		 return "redirect:/viewstudents";
	}
	
	@GetMapping("/student/{studentId}/email")
	public String showStudentEmailForm(@PathVariable("studentId") Integer studentId, Model model) {
		model.addAttribute("student", studentService.getStudent(studentId));
		return "students/studentemail";
	}
	
	@GetMapping("/emailAll")
	public String showEmailForm() {
		return "students/studentemail"; 
	}
	@PostMapping("/searchStudent")
	public String searchStudent(@RequestParam("searchStudent") String search, Model model) {
		Map<Student,String> studentProfiles = new LinkedHashMap<Student, String>();  
		for(Student s: studentService.getAllStudents()) {
			if (s.toString().contains(search)) {
				if(s.getProfilePic() == null) {
					studentProfiles.put(s, "");
				}
				else {
					studentProfiles.put(s, Base64.getEncoder().encodeToString(s.getProfilePic())); 
				}
			}
		}

		model.addAttribute("studentProfiles", studentProfiles);
		return "students/studentlisting";
		
	}
	@PostMapping("/sendEmail")
	public String sendEmailToStudent(@RequestParam("subject") String subject, @RequestParam("content") String content, Model model){
		if (model.containsAttribute("student")) {
			Student student = (Student) model.asMap().get("student");  // retrieve id from student in model by converting model to map.  
			emailService.sendEmailToStudent(student.getStudentId(), subject, content);
		}
		else {
			emailService.sendEmailToAllStudents(subject, content);
		}	
		return "redirect:/viewstudents";
	}

	
	@PostMapping("/addstudent")
	public String saveOrUpdateStudent(@RequestParam("photoId") MultipartFile photoId, @RequestParam("progressFile") MultipartFile progressFile,  @ModelAttribute("student") @Valid Student student, BindingResult result) throws IOException {
		
		if(result.hasErrors()) {
			System.out.println("Errors found in student"); 
			return "students/studentform";
		}
		else {
			byte[] photoBytes; 
			if (photoId.isEmpty() == false && photoId != null) { // check of the pic file is chosen by user 
				photoBytes = new byte[(int) photoId.getBytes().length]; // initializing byte array size 
				photoId.getInputStream().read(photoBytes); 
				student.setProfilePic(photoBytes);
			}
			byte[] report; 
			if (progressFile.isEmpty() == false && progressFile != null) { // check of the pic file is chosen by user 
				report = new byte[(int) progressFile.getBytes().length]; // initializing byte array size 
				progressFile.getInputStream().read(report); 
				student.setProgressReport(report);
			}
			
			studentService.addOrUpdateStudent(student);
			return "redirect:/viewstudents"; 
			
		}			
	}

	 @GetMapping("/student/{studentId}/studentreport")
	 public ResponseEntity<byte[]> handle(@PathVariable("studentId") Integer studentId) {
		 HttpHeaders headers = new HttpHeaders();
		 headers.setContentType(MediaType.parseMediaType("application/pdf"));
		 String filename = "report.pdf";
		    headers.add("content-disposition","inline;filename=" + filename); // setting default variables eg. filename
		    headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
		    ResponseEntity<byte[]> response = new ResponseEntity<byte[]>(
		            studentService.getStudent(studentId).getProgressReport(), headers, HttpStatus.OK);
		    return response;
	 }

	
//	@GetMapping("/studentlisting")
//	public String showStudentListing() {
//		return "redirect:/viewstudents";
//	}
	
	
	
}
