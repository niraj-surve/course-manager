package com.course.manager.app.service;

import java.util.List;
import java.util.Map;

public interface ReportingService {
	List<String> generateStudentReport();

	List<String> generateCourseReport();

	Map<String, List<String>> generateEnrollmentReport();
}
