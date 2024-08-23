package com.course.manager.app.service;

import java.util.List;

import com.course.manager.app.model.Course;

public interface CourseService {
	String addCourse(String code, String name, String instructorName);

	Course getCourse(String code);

	List<Course> showAllCourses();

	String removeCourse(String code);
}
