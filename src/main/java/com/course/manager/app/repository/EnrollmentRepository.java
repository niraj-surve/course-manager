package com.course.manager.app.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

import com.course.manager.app.model.Enrollment;
import com.course.manager.app.util.DatabaseConnection;

public class EnrollmentRepository {

	public String save(Enrollment enrollment) {
		String sql = "INSERT INTO enrollments (studentId, courseCode) VALUES (?, ?)";

		try (Connection conn = DatabaseConnection.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setInt(1, enrollment.getStudentId());
			pstmt.setString(2, enrollment.getCourseCode());
			pstmt.executeUpdate();
			return "Enrollment successful!";
		} catch (SQLException e) {
			if (e.getErrorCode() == 1062) { // Duplicate entry error code
				return "Enrollment already exists!";
			}
			e.printStackTrace();
			return "Error occurred: " + e.getMessage();
		}
	}

	public String remove(int studentId, String courseCode) {
		String sql = "DELETE FROM enrollments WHERE studentId = ? AND courseCode = ?";

		try (Connection conn = DatabaseConnection.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setInt(1, studentId);
			pstmt.setString(2, courseCode);
			int rowsAffected = pstmt.executeUpdate();

			if (rowsAffected > 0) {
				return "Enrollment removed successfully!";
			} else {
				return "Enrollment not found!";
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return "Error occurred: " + e.getMessage();
		}
	}

	public Set<Enrollment> findAll() {
		Set<Enrollment> enrollments = new HashSet<>();
		String sql = "SELECT * FROM enrollments";

		try (Connection conn = DatabaseConnection.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery()) {

			while (rs.next()) {
				Enrollment enrollment = new Enrollment(rs.getInt("studentId"), rs.getString("courseCode"));
				enrollments.add(enrollment);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return enrollments;
	}

	public Set<Enrollment> findByStudentId(int studentId) {
		Set<Enrollment> studentEnrollments = new HashSet<>();
		String sql = "SELECT * FROM enrollments WHERE studentId = ?";

		try (Connection conn = DatabaseConnection.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setInt(1, studentId);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				Enrollment enrollment = new Enrollment(rs.getInt("studentId"), rs.getString("courseCode"));
				studentEnrollments.add(enrollment);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return studentEnrollments;
	}

	public Set<Enrollment> findByCourseCode(String courseCode) {
		Set<Enrollment> courseEnrollments = new HashSet<>();
		String sql = "SELECT * FROM enrollments WHERE courseCode = ?";

		try (Connection conn = DatabaseConnection.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, courseCode);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				Enrollment enrollment = new Enrollment(rs.getInt("studentId"), rs.getString("courseCode"));
				courseEnrollments.add(enrollment);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return courseEnrollments;
	}

	public Set<String> findEnrollmentDetails() {
		Set<String> enrollmentDetails = new HashSet<>();
		String sql = "SELECT e.studentId, s.name AS studentName, s.email AS studentEmail, c.code, c.name AS name, c.instructorName "
				+ "FROM enrollments e " + "JOIN students s ON e.studentId = s.id "
				+ "JOIN courses c ON e.courseCode = c.code";

		try (Connection conn = DatabaseConnection.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery()) {

			while (rs.next()) {
				String details = "Student ID: " + rs.getInt("studentId") + ", Name: " + rs.getString("studentName")
						+ ", Email: " + rs.getString("studentEmail") + ", Course Code: " + rs.getString("code")
						+ ", Course Name: " + rs.getString("name") + ", Instructor: " + rs.getString("instructorName");
				enrollmentDetails.add(details);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return enrollmentDetails;
	}

}
