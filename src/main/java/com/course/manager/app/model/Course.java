package com.course.manager.app.model;

public class Course {
	private String code;
	private String name;
	private String instructorName;

	public Course() {
	}

	public Course(String code, String name, String instructorName) {
		this.code = code;
		this.name = name;
		this.instructorName = instructorName;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getInstructorName() {
		return instructorName;
	}

	public void setInstructorName(String instructorName) {
		this.instructorName = instructorName;
	}

	@Override
	public String toString() {
		return "Course [code=" + code + ", name=" + name + ", instructorName=" + instructorName + "]";
	}
}
