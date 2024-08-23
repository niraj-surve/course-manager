package com.course.manager.app.service.impl;

import java.util.Set;

import com.course.manager.app.model.Enrollment;
import com.course.manager.app.repository.EnrollmentRepository;
import com.course.manager.app.service.EnrollmentService;

public class EnrollmentServiceImpl implements EnrollmentService {

	private EnrollmentRepository enrollmentRepository;

	public EnrollmentServiceImpl(EnrollmentRepository enrollmentRepository) {
		this.enrollmentRepository = enrollmentRepository;
	}

	@Override
	public String enrollStudent(int studentId, String courseCode) {
		Enrollment enrollment = new Enrollment(studentId, courseCode);
		return enrollmentRepository.save(enrollment);
	}

	@Override
	public String removeEnrollment(int studentId, String courseCode) {
		return enrollmentRepository.remove(studentId, courseCode);
	}

	@Override
	public Set<Enrollment> showAllEnrollments() {
		return enrollmentRepository.findAll();
	}

	@Override
	public Set<Enrollment> getEnrollmentsByStudent(int studentId) {
		return enrollmentRepository.findByStudentId(studentId);
	}

	@Override
	public Set<Enrollment> getEnrollmentsByCourse(String courseCode) {
		return enrollmentRepository.findByCourseCode(courseCode);
	}
}
