package com.spring.project.domain;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Course {

	@NotBlank
	@Size(min = 3, max = 200)
	private String courseName; // automatically mapped to VARCHAR
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // Unique Primary key 
	private Integer courseId; 
	
	@NotNull
    @Min(value = 1)
	@Max(value = 50)
	private Integer credits; 
	
	@NotNull
    @Min(value = 1)
	@Max(value = 5)
	private Integer duration; 
	@OneToMany(mappedBy = "course") 
	private List<StudentCourse> studentCourseList = new ArrayList<StudentCourse>();
	@Lob
	
	private String description ;
	
	@Enumerated(EnumType.STRING) // save as word "BASIC" instead of number 1
	
	@NotNull
	private CourseLevel courseLevel; 
	public Course() {
		
	}
	public Course(String courseName, Integer credits, Integer duration, String description,
			CourseLevel courseLevel) {
		this.courseName = courseName;
		this.credits = credits;
		this.duration = duration;
		this.description = description;
		this.courseLevel = courseLevel;
	}

	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	public Integer getCourseId() {
		return courseId;
	}
	public void setCourseId(Integer courseId) {
		this.courseId = courseId;
	}
	public Integer getCredits() {
		return credits;
	}
	public void setCredits(Integer credits) {
		this.credits = credits;
	}
	public CourseLevel getCourseLevel() {
		return courseLevel;
	}
	public void setCourseLevel(CourseLevel courseLevel) {
		this.courseLevel = courseLevel;
	}
	public Integer getDuration() {
		return duration;
	}
	public void setDuration(Integer duration) {
		this.duration = duration;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	} 
	
	public List<StudentCourse> getStudentCourseList() {
		return studentCourseList;
	}
	public void setStudentCourseList(List<StudentCourse> studentCourseList) {
		this.studentCourseList = studentCourseList;
	}
	@Override
	public String toString() {
		return "Course [courseName=" + courseName + " credits=" + credits + ", duration="
				+ duration + ", description=" + description + ", courseLevel=" + courseLevel + "]";
	}


    
	
	

}
