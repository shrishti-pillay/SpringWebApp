package com.spring.project.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.spring.project.domain.StudentCourse;
import com.spring.project.services.CourseService;
import com.spring.project.services.StudentCourseService;
import com.spring.project.services.StudentService;


@Controller
public class StudentCourseController {

	private final StudentService studentService; 
	private final CourseService courseService; 
	private final StudentCourseService studentCourseService; 
	
	@Autowired
	public StudentCourseController(StudentService studentService, CourseService courseService,
			StudentCourseService studentCourseService) {
		this.studentService = studentService;
		this.courseService = courseService;
		this.studentCourseService = studentCourseService;
	}
	
	
	 // studentCourse 
		@GetMapping("/student/{studentId}/studentcourse")
		public String showStudentCoursesById(@PathVariable("studentId") Integer studentId, Model model) {
			model.addAttribute("student", studentService.getStudent(studentId));
			model.addAttribute("masterCourseList", courseService.getAllCourses());
			return "students/studentcourse";
		}
		
		@PostMapping("/addStudentCourse")
		public String addStudentCourseById(@RequestParam("course") Integer courseId, @RequestParam("studentId") Integer studentId, Model model) {
			boolean flag = true; 
			for (StudentCourse s: studentService.getStudent(studentId).getStudentCourseList())
			{
				if (s.getCourse().getCourseId() == courseId)
				{
					flag = false; 
					break; 
				}
			}
			if (flag == false ) {
				model.addAttribute("message", "*Student has already registered for "+courseService.getCourse(courseId).getCourseName()+".");
				model.addAttribute("student", studentService.getStudent(studentId));
				model.addAttribute("masterCourseList", courseService.getAllCourses());
				return "students/studentcourse";
			}
			else {
				studentCourseService.addStudentCourse(studentId, courseId);
				return "redirect:/student/"+studentId+"/studentcourse";
			}				
		}
		
		@GetMapping("/studentcourse/{studentId}/leavecourse/{courseId}")
		public String deleteStudentCourse(@PathVariable("studentId") Integer studentId, @PathVariable("courseId") Integer courseId, Model model) {
			studentCourseService.deleteStudentCourse(studentId, courseId);
			return "redirect:/student/"+studentId+"/studentcourse";
		}

}
