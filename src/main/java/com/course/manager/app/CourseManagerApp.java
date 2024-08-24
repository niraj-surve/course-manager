package com.course.manager.app;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import com.course.manager.app.model.Course;
import com.course.manager.app.model.Enrollment;
import com.course.manager.app.model.Student;
import com.course.manager.app.repository.CourseRepository;
import com.course.manager.app.repository.EnrollmentRepository;
import com.course.manager.app.repository.StudentRepository;
import com.course.manager.app.service.CourseService;
import com.course.manager.app.service.EnrollmentService;
import com.course.manager.app.service.ReportingService;
import com.course.manager.app.service.StudentService;
import com.course.manager.app.service.impl.CourseServiceImpl;
import com.course.manager.app.service.impl.EnrollmentServiceImpl;
import com.course.manager.app.service.impl.ReportingServiceImpl;
import com.course.manager.app.service.impl.StudentServiceImpl;

public class CourseManagerApp {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		StudentRepository studentRepository = new StudentRepository();
		StudentService studentService = new StudentServiceImpl(studentRepository);

		CourseRepository courseRepository = new CourseRepository();
		CourseService courseService = new CourseServiceImpl(courseRepository);

		EnrollmentRepository enrollmentRepository = new EnrollmentRepository();
		EnrollmentService enrollmentService = new EnrollmentServiceImpl(enrollmentRepository);

		ReportingService reportingService = new ReportingServiceImpl(studentRepository, courseRepository,
				enrollmentRepository);

		int choice = -1;

		while (true) {
			try {
				System.out.println("***** COURSE MANAGER *****");
				System.out.print("1. Student Management" + "\n2. Course Management" + "\n3. Enrollment Management"
						+ "\n4. Reporting" + "\n\nEnter your choice (0 to exit) -> ");
				choice = sc.nextInt();
				sc.nextLine();

				switch (choice) {
				case 1:
					handleStudentManagement(sc, studentService);
					break;
				case 2:
					handleCourseManagement(sc, courseService);
					break;
				case 3:
					handleEnrollmentManagement(sc, enrollmentService);
					break;
				case 4:
					handleReporting(sc, reportingService);
					break;
				case 0:
					System.out.println("Exiting the application...");
					sc.close();
					return;
				default:
					System.out.println("Invalid choice. Please try again.");
					break;
				}
			} catch (InputMismatchException e) {
				System.out.println("Please enter a valid input!");
				sc.nextLine();
			}
		}
	}

	private static void handleStudentManagement(Scanner sc, StudentService studentService) {
		int innerChoice = -1;

		while (innerChoice != 0) {
			try {
				System.out.println("\n***** STUDENT MANAGEMENT *****");
				System.out.print("1. Add a New Student" + "\n2. Remove a Student" + "\n3. Search Student Details"
						+ "\n4. Show All Students" + "\n\nEnter your choice (0 to go back) -> ");
				innerChoice = sc.nextInt();
				sc.nextLine();

				switch (innerChoice) {
				case 1:
					String name = getStringInput(sc, "Enter student name -> ");
					String email = getStringInput(sc, "Enter student email -> ");
					String department = getStringInput(sc, "Enter student department -> ");
					System.out.println(studentService.addStudent(name, email, department));
					break;
				case 2:
					email = getStringInput(sc, "Enter student email -> ");
					System.out.println(studentService.removeStudent(email));
					break;
				case 3:
					email = getStringInput(sc, "Enter student email -> ");
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

			} catch (InputMismatchException e) {
				System.out.println("Please enter a valid input!");
				sc.nextLine();
			}
		}
	}

	private static void handleCourseManagement(Scanner sc, CourseService courseService) {
		int innerChoice = -1;
		while (innerChoice != 0) {
			try {
				System.out.println("\n***** COURSE MANAGEMENT *****");
				System.out.print("1. Add a New Course" + "\n2. Remove a Course" + "\n3. Search Course Details"
						+ "\n4. Show All Courses" + "\n\nEnter your choice (0 to go back) -> ");
				innerChoice = sc.nextInt();
				sc.nextLine();

				switch (innerChoice) {
				case 1:
					String code = getStringInput(sc, "Enter course code -> ");
					String name = getStringInput(sc, "Enter course name -> ");
					String instructorName = getStringInput(sc, "Enter instructor name -> ");
					System.out.println(courseService.addCourse(code, name, instructorName));
					break;
				case 2:
					code = getStringInput(sc, "Enter course code -> ");
					System.out.println(courseService.removeCourse(code));
					break;
				case 3:
					code = getStringInput(sc, "Enter course code -> ");
					Course course = courseService.getCourse(code);
					System.out.println(course.toString());
					break;
				case 4:
					System.out.println("***** ALL COURSES *****");
					List<Course> courses = courseService.showAllCourses();
					if (courses.isEmpty()) {
						System.out.println("Courses data not available!");
					} else {
						for (Course cs : courses) {
							System.out.println(cs.toString());
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

			} catch (InputMismatchException e) {
				System.out.println("Please enter a valid input!");
				sc.nextLine();
			}
		}
	}

	private static void handleEnrollmentManagement(Scanner sc, EnrollmentService enrollmentService) {
		int innerChoice = -1;
		while (innerChoice != 0) {
			try {
				System.out.println("\n***** ENROLLMENT MANAGEMENT *****");
				System.out.print("1. Enroll a Student in a Course" + "\n2. Remove an Enrollment"
						+ "\n3. Show All Enrollments" + "\n4. Show Enrollments by Student"
						+ "\n5. Show Enrollments by Course" + "\n\nEnter your choice (0 to go back) -> ");
				innerChoice = sc.nextInt();
				sc.nextLine();

				switch (innerChoice) {
				case 1:
					System.out.print("\nEnter student id -> ");
					int studentId = sc.nextInt();
					System.out.print("Enter course code -> ");
					String courseCode = sc.next();
					System.out.println(enrollmentService.enrollStudent(studentId, courseCode));
					break;
				case 2:
					System.out.print("\nEnter student id -> ");
					studentId = sc.nextInt();
					System.out.print("Enter course code -> ");
					courseCode = sc.next();
					System.out.println(enrollmentService.removeEnrollment(studentId, courseCode));
					break;
				case 3:
					Set<Enrollment> allEnrollments = enrollmentService.showAllEnrollments();
					if (allEnrollments.isEmpty()) {
						System.out.println("No enrollments found.");
					} else {
						for (Enrollment enrollment : allEnrollments) {
							System.out.println(enrollment.toString());
						}
					}
					break;
				case 4:
					System.out.print("\nEnter student id -> ");
					studentId = sc.nextInt();
					Set<Enrollment> studentEnrollments = enrollmentService.getEnrollmentsByStudent(studentId);
					if (studentEnrollments.isEmpty()) {
						System.out.println("No enrollments found for this student.");
					} else {
						for (Enrollment enrollment : studentEnrollments) {
							System.out.println(enrollment.toString());
						}
					}
					break;
				case 5:
					System.out.print("\nEnter course code -> ");
					courseCode = sc.next();
					Set<Enrollment> courseEnrollments = enrollmentService.getEnrollmentsByCourse(courseCode);
					if (courseEnrollments.isEmpty()) {
						System.out.println("No enrollments found for this course.");
					} else {
						for (Enrollment enrollment : courseEnrollments) {
							System.out.println(enrollment.toString());
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
			} catch (InputMismatchException e) {
				System.out.println("Please enter a valid input!");
				sc.nextLine();
			}
		}
	}

	private static void handleReporting(Scanner sc, ReportingService reportingService) {
		int innerChoice = -1;
		while (innerChoice != 0) {
			try {
				System.out.println("\n***** REPORTING *****");
				System.out.print("1. Show Student Enrollment Report" + "\n2. Show Course Enrollment Report"
						+ "\n3. Show Overall Enrollment Summary" + "\n\nEnter your choice (0 to go back) -> ");
				innerChoice = sc.nextInt();
				sc.nextLine();

				switch (innerChoice) {
				case 1:
					Map<String, List<String>> enrollmentReport = reportingService.generateEnrollmentReport();
					System.out.println("***** ENROLLMENT REPORT *****");
					for (Map.Entry<String, List<String>> entry : enrollmentReport.entrySet()) {
						System.out.println("Course Code: " + entry.getKey());
						for (String line : entry.getValue()) {
							System.out.println(line);
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
			} catch (InputMismatchException e) {
				System.out.println("Please enter a valid input!");
				sc.nextLine();
			}
		}
	}

	private static String getStringInput(Scanner sc, String prompt) {
		String input;
		while (true) {
			System.out.print(prompt);
			input = sc.nextLine();
			if (input.trim().isEmpty()) {
				System.out.println("Input cannot be empty. Please enter a valid string.");
			} else {
				try {
					Integer.parseInt(input);
					System.out.println("Input cannot be a number. Please enter a valid string.");
				} catch (NumberFormatException e) {
					break;
				}
			}
		}
		return input;
	}
}