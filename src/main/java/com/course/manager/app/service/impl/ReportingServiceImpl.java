package com.course.manager.app.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.course.manager.app.model.Course;
import com.course.manager.app.model.Student;
import com.course.manager.app.repository.CourseRepository;
import com.course.manager.app.repository.EnrollmentRepository;
import com.course.manager.app.repository.StudentRepository;
import com.course.manager.app.service.ReportingService;

public class ReportingServiceImpl implements ReportingService {

	private StudentRepository studentRepository;
	private CourseRepository courseRepository;
	private EnrollmentRepository enrollmentRepository;

	public ReportingServiceImpl(StudentRepository studentRepository, CourseRepository courseRepository,
			EnrollmentRepository enrollmentRepository) {
		this.studentRepository = studentRepository;
		this.courseRepository = courseRepository;
		this.enrollmentRepository = enrollmentRepository;
	}

	@Override
	public List<String> generateStudentReport() {
		List<String> report = new ArrayList<>();
		List<Student> students = studentRepository.showAll();

		if (students.isEmpty()) {
			report.add("No students found.");
		} else {
			for (Student student : students) {
				report.add(student.toString());
			}
		}
		return report;
	}

	@Override
	public List<String> generateCourseReport() {
		List<String> report = new ArrayList<>();
		List<Course> courses = courseRepository.showAll();

		if (courses.isEmpty()) {
			report.add("No courses found.");
		} else {
			for (Course course : courses) {
				report.add(course.toString());
			}
		}
		return report;
	}

	@Override
	public Map<String, List<String>> generateEnrollmentReport() {
		Set<String> enrollments = enrollmentRepository.findEnrollmentDetails();
		Map<String, List<String>> report = new HashMap<>();

		for (String enrollment : enrollments) {
			String[] parts = enrollment.split(", ");
			String courseCode = parts[3].split(": ")[1];

			if (!report.containsKey(courseCode)) {
				report.put(courseCode, new ArrayList<>());
			}
			report.get(courseCode).add(enrollment);
		}

		return report;
	}

}
