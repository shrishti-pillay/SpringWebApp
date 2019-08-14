package com.spring.project.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.spring.project.domain.Course;
import com.spring.project.services.CourseService;


@Controller
public class CourseController {

	private final CourseService courseService;

	@Autowired
	public CourseController(CourseService courseService) {
		this.courseService = courseService;
	}
	//courses
	
		@GetMapping("/viewcourses")
		public String showCourseListing(Model model)
		{
			List<Course> courseList = courseService.getAllCourses(); 
			model.addAttribute("courseList", courseList); // key- value pair
			System.out.println("Navigating to course listing page.");
			return "courses/courselisting";
		}
		
		@PostMapping("/searchCourse")
		public String searchStudent(@RequestParam("searchCourse") String search, @RequestParam("searchCourseQuery") String searchQuery,  Model model)
		{
			if(searchQuery.equals("courseName") )
			{
				model.addAttribute("courseList", courseService.findByCourseName(search));
			}
			else {
				 String s[] = search.split(",");
				model.addAttribute("courseList", courseService.findByCreditsAndDuration(Integer.parseInt(s[0]), Integer.parseInt(s[1])));
			}			
			return "courses/courselisting"; 			
		}
		
		@GetMapping("/course/{courseId}/delete") // path variable
		public String deleteCourseById(@PathVariable("courseId") Integer courseId) {
			courseService.deleteCourse(courseId); 
			return "redirect:/viewcourses"; // call the showCourseListing by redirecting to page url 
		}
		
		
		@GetMapping("/course/{courseId}/edit") // path variable
		public String editCourseById(@PathVariable("courseId") Integer courseId, Model model) {
			model.addAttribute("course", courseService.getCourse(courseId));
			return "courses/courseform"; // call the showCourseListing by redirecting to page url 
		}
		
		@GetMapping("course/{courseId}/students")
		public String showCourseStudents(@PathVariable("courseId") Integer courseId, Model model) {
			model.addAttribute("course", courseService.getCourse(courseId)); 
			return "courses/coursestudent";
		}
		
		@GetMapping("/courseform")
		public String showCourseForm(Model model) {
			model.addAttribute("course", new Course()); // send empty object to add new course values 
			return "courses/courseform";
		}
		
		@PostMapping("/addcourse")
		public String saveOrUpdateCourse(@ModelAttribute("course") @Valid Course course, BindingResult result ) {// checks which fields have passes and failed
			if(result.hasErrors()) {
				System.out.println("Errors found in course"); 
				return "courses/courseform";
			}
			else {
				courseService.addOrUpdateCourse(course);
				return "redirect:/viewcourses"; 
				
			}	
		}
		
		@GetMapping("/courselisting")
		public String showCourseListing() {
			return "redirect:/viewcourses";
		}
}
