package com.course.manager.app.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.course.manager.app.exceptions.StudentNotFoundException;
import com.course.manager.app.model.Student;
import com.course.manager.app.util.DatabaseConnection;

public class StudentRepository {

	public String save(Student student) {
		String checkSql = "SELECT * FROM students WHERE email = ?";
		String insertSql = "INSERT INTO students (name, email, department) VALUES (?, ?, ?)";

		try (Connection conn = DatabaseConnection.getConnection();
				PreparedStatement checkPstmt = conn.prepareStatement(checkSql);
				PreparedStatement insertPstmt = conn.prepareStatement(insertSql)) {

			// Check if student already exists
			checkPstmt.setString(1, student.getEmail());
			ResultSet rs = checkPstmt.executeQuery();

			if (rs.next()) {
				return "Student with email " + student.getEmail() + " already exists!";
			}

			// If student does not exist, insert the new student
			insertPstmt.setString(1, student.getName());
			insertPstmt.setString(2, student.getEmail());
			insertPstmt.setString(3, student.getDepartment());
			insertPstmt.executeUpdate();
			return "Student with name " + student.getName() + " added successfully!";

		} catch (SQLException e) {
			e.printStackTrace();
			return "Error occurred: " + e.getMessage();
		}
	}

	public Student find(String email) throws StudentNotFoundException {
		String sql = "SELECT * FROM students WHERE email = ?";

		try (Connection conn = DatabaseConnection.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, email);
			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				return new Student(rs.getString("name"), rs.getString("email"), rs.getString("department"));
			} else {
				throw new StudentNotFoundException("Student with email " + email + " not found!");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new StudentNotFoundException("Database error occurred: " + e.getMessage());
		}
	}

	public List<Student> showAll() {
		List<Student> students = new ArrayList<>();
		String sql = "SELECT * FROM students";

		try (Connection conn = DatabaseConnection.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery()) {

			while (rs.next()) {
				Student student = new Student(rs.getString("name"), rs.getString("email"), rs.getString("department"));
				students.add(student);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return students;
	}

	public String remove(String email) {
		String sql = "DELETE FROM students WHERE email = ?";

		try (Connection conn = DatabaseConnection.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, email);
			int rowsAffected = pstmt.executeUpdate();

			if (rowsAffected > 0) {
				return "Student with email " + email + " removed successfully!";
			} else {
				return "Student with email " + email + " not found!";
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return "Error occurred: " + e.getMessage();
		}
	}
}
