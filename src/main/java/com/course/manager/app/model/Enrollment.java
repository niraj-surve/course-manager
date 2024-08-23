package com.course.manager.app.model;

public class Enrollment {
	private int studentId;
	private String courseCode;

	public Enrollment(int studentId, String courseCode) {
		this.studentId = studentId;
		this.courseCode = courseCode;
	}

	public int getStudentId() {
		return studentId;
	}

	public String getCourseCode() {
		return courseCode;
	}

	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}

	public void setCourseCode(String courseCode) {
		this.courseCode = courseCode;
	}

	@Override
	public String toString() {
		return "Enrollment{" + "studentId='" + studentId + '\'' + ", courseCode='" + courseCode + '\'' + '}';
	}
}
