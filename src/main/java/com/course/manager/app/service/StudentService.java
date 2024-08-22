package com.course.manager.app.service;

import java.util.List;

import com.course.manager.app.model.Student;

public interface StudentService {
	String addStudent(String name, String email, String department);

	Student getStudent(String email);

	List<Student> showAllStudents();

	String removeStudent(String email);
}
