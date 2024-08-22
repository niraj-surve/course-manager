package com.course.manager.app.service.impl;

import java.util.List;

import com.course.manager.app.exceptions.StudentNotFoundException;
import com.course.manager.app.model.Student;
import com.course.manager.app.repository.StudentRepository;
import com.course.manager.app.service.StudentService;

public class StudentServiceImpl implements StudentService {

	private StudentRepository students;

	public StudentServiceImpl(StudentRepository students) {
		this.students = students;
	}

	@Override
	public String addStudent(String name, String email, String department) {
		Student student = new Student(name, email, department);
		return students.save(student);
	}

	@Override
	public Student getStudent(String email) {
		try {
			return students.find(email);
		} catch (StudentNotFoundException e) {
			System.out.println("Student with email " + email + " not found!");
			return null;
		}
	}

	@Override
	public List<Student> showAllStudents() {
		return students.showAll();
	}

	@Override
	public String removeStudent(String email) {
		return students.remove(email);
	}

}
