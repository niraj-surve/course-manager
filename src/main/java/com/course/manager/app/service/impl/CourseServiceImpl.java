package com.course.manager.app.service.impl;

import java.util.List;

import com.course.manager.app.exceptions.CourseNotFoundException;
import com.course.manager.app.model.Course;
import com.course.manager.app.repository.CourseRepository;
import com.course.manager.app.service.CourseService;

public class CourseServiceImpl implements CourseService {

	private CourseRepository courses;

	public CourseServiceImpl(CourseRepository courses) {
		this.courses = courses;
	}

	@Override
	public String addCourse(String code, String name, String instructorName) {
		Course course = new Course(code, name, instructorName);
		return courses.save(course);
	}

	@Override
	public Course getCourse(String code) {
		try {
			return courses.find(code);
		} catch (CourseNotFoundException e) {
			System.out.println("Course with code " + code + " not found!");
			return null;
		}
	}

	@Override
	public List<Course> showAllCourses() {
		return courses.showAll();
	}

	@Override
	public String removeCourse(String code) {
		return courses.remove(code);
	}

}
