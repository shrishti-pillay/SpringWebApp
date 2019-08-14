package com.spring.project.repositories;

import org.springframework.data.repository.CrudRepository;

import com.spring.project.domain.StudentCourse;

public interface StudentCourseRepository extends CrudRepository<StudentCourse, Integer> {

}
