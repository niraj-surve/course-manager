package com.course.manager.app.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.course.manager.app.exceptions.CourseNotFoundException;
import com.course.manager.app.model.Course;
import com.course.manager.app.util.DatabaseConnection;

public class CourseRepository {

	public String save(Course course) {
		String checkSql = "SELECT * FROM courses WHERE code = ?";
		String insertSql = "INSERT INTO courses (code, name, instructorName) VALUES (?, ?, ?)";

		try (Connection conn = DatabaseConnection.getConnection();
				PreparedStatement checkPstmt = conn.prepareStatement(checkSql);
				PreparedStatement insertPstmt = conn.prepareStatement(insertSql)) {

			// Check if course already exists
			checkPstmt.setString(1, course.getCode());
			ResultSet rs = checkPstmt.executeQuery();

			if (rs.next()) {
				return "Course with code " + course.getCode() + " already exists!";
			}

			// If course does not exist, insert the new course
			insertPstmt.setString(1, course.getCode());
			insertPstmt.setString(2, course.getName());
			insertPstmt.setString(3, course.getInstructorName());
			insertPstmt.executeUpdate();
			return "Course with name " + course.getName() + " added successfully!";

		} catch (SQLException e) {
			e.printStackTrace();
			return "Error occurred: " + e.getMessage();
		}
	}

	public Course find(String code) throws CourseNotFoundException {
		String sql = "SELECT * FROM courses WHERE code = ?";

		try (Connection conn = DatabaseConnection.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, code);
			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				return new Course(rs.getString("code"), rs.getString("name"), rs.getString("instructorName"));
			} else {
				throw new CourseNotFoundException("Course with code " + code + " not found!");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new CourseNotFoundException("Database error occurred: " + e.getMessage());
		}
	}

	public List<Course> showAll() {
		List<Course> courses = new ArrayList<>();
		String sql = "SELECT * FROM courses";

		try (Connection conn = DatabaseConnection.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery()) {

			while (rs.next()) {
				Course course = new Course(rs.getString("code"), rs.getString("name"), rs.getString("instructorName"));
				courses.add(course);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return courses;
	}

	public String remove(String code) {
		String sql = "DELETE FROM courses WHERE code = ?";

		try (Connection conn = DatabaseConnection.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, code);
			int rowsAffected = pstmt.executeUpdate();

			if (rowsAffected > 0) {
				return "Course with code " + code + " removed successfully!";
			} else {
				return "Course with code " + code + " not found!";
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return "Error occurred: " + e.getMessage();
		}
	}
}
