package com.spring.project.domain;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.spring.project.validations.Phone;

@Entity
public class Student {
	
	@NotBlank
	@Size(min = 3, max = 200)
	private String studentName; 
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY )
	private Integer studentId; 
	
	@NotBlank
	@Email
	private String email; 
	@OneToMany(mappedBy = "student") // SQL - CASCADE ALL 
	private List<StudentCourse> studentCourseList = new ArrayList<StudentCourse>();
	
	@NotBlank
	@Phone
	private String phoneNum;
	@Lob // Large object -- used for image, audio, video, etc
	private byte[] profilePic; 
	@Lob 
	private byte[] progressReport; 
	public Student() {
		
	}
	public Student(String studentName, String email, String phoneNum) {
		this.studentName = studentName;
		this.email = email;
		this.phoneNum = phoneNum;
		
	}
	public String getStudentName() {
		return studentName;
	}
	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}
	public Integer getStudentId() {
		return studentId;
	}
	public void setStudentId(Integer studentId) {
		this.studentId = studentId;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public List<StudentCourse> getStudentCourseList() {
		return studentCourseList;
	}
	public void setStudentCourseList(List<StudentCourse> studentCourseList) {
		this.studentCourseList = studentCourseList;
	}
	public String getPhoneNum() {
		return phoneNum;
	}
	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}
	public byte[] getProfilePic() {
		return profilePic;
	}
	public void setProfilePic(byte[] profilePic) {
		this.profilePic = profilePic;
	}
	public byte[] getProgressReport() {
		return progressReport;
	}
	public void setProgressReport(byte[] progressReport) {
		this.progressReport = progressReport;
	}
	@Override
	public String toString() {
		return "Student [studentName=" + studentName + ", studentId=" + studentId + ", email=" + email + ", studentCourseList="
				+ studentCourseList + ", phoneNum=" + phoneNum + "]";
	}
}
	
