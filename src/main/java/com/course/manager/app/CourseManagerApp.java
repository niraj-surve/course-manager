package com.course.manager.app;

import java.util.List;
import java.util.Scanner;

import com.course.manager.app.model.Student;
import com.course.manager.app.repository.StudentRepository;
import com.course.manager.app.service.StudentService;
import com.course.manager.app.service.impl.StudentServiceImpl;

public class CourseManagerApp {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		StudentRepository studentRepository = new StudentRepository();
		StudentService studentService = new StudentServiceImpl(studentRepository);

		int choice = -1;
		while (choice != 0) {
			System.out.println("***** COURSE MANAGER *****");
			System.out.print("1. Student Management" + "\n2. Course Management" + "\n3. Enrollment Management"
					+ "\n4. Reporting" + "\n\nEnter your choice (0 to exit) -> ");
			choice = sc.nextInt();

			switch (choice) {
			case 1: {
				int innerChoice = -1;
				while (innerChoice != 0) {
					System.out.println("\n***** STUDENT MANAGEMENT *****");
					System.out.print("1. Add a New Student" + "\n2. Remove a Student" + "\n3. Search Student Details"
							+ "\n4. Show All Students" + "\n\nEnter your choice (0 to go back) -> ");
					innerChoice = sc.nextInt();

					switch (innerChoice) {
					case 1:
						System.out.print("\nEnter student's name -> ");
						String name = sc.next();
						System.out.print("Enter student's email -> ");
						String email = sc.next();
						System.out.print("Enter student's department -> ");
						String department = sc.next();
						System.out.println(studentService.addStudent(name, email, department));
						break;
					case 2:
						System.out.print("\nEnter student's email -> ");
						email = sc.next();
						System.out.println(studentService.removeStudent(email));
						break;
					case 3:
						System.out.print("\nEnter student's email -> ");
						email = sc.next();
						Student student = studentService.getStudent(email);
						System.out.println(student.toString());
						break;
					case 4:
						System.out.println("***** ALL STUDENTS *****");
						List<Student> students = studentService.showAllStudents();
						if (students.isEmpty()) {
							System.out.println("Students data not available!");
						} else {
							for (Student stud : students) {
								System.out.println(stud.toString());
							}
						}
						break;
					case 0:
						System.out.println("\nReturning to Main Menu...");
						break;
					default:
						System.out.println("\nInvalid choice. Please try again.");
						break;
					}
				}
				break;
			}
			case 2: {
				System.out.println("Course Management coming soon...");
				break;
			}
			case 3: {
				System.out.println("Enrollment Management coming soon...");
				break;
			}
			case 4: {
				System.out.println("Reporting coming soon...");
				break;
			}
			case 0:
				System.out.println("Exiting the application...");
				break;
			default:
				System.out.println("Invalid choice. Please try again.");
				break;
			}
		}
		sc.close();
	}
}
