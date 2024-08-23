package com.course.manager.app.service;

import java.util.Set;

import com.course.manager.app.model.Enrollment;

public interface EnrollmentService {
	String enrollStudent(int studentId, String courseCode);

	String removeEnrollment(int studentId, String courseCode);

	Set<Enrollment> showAllEnrollments();

	Set<Enrollment> getEnrollmentsByStudent(int studentId);

	Set<Enrollment> getEnrollmentsByCourse(String courseCode);
}
