package com.spring.project.repositories;

import org.springframework.data.repository.CrudRepository;

import com.spring.project.domain.Student;

public interface StudentRepository extends CrudRepository<Student, Integer> {

}
