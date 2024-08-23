package com.course.manager.app;

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
		while (choice != 0) {
			System.out.println("***** COURSE MANAGER *****");
			System.out.print("1. Student Management" + "\n2. Course Management" + "\n3. Enrollment Management"
					+ "\n4. Reporting" + "\n\nEnter your choice (0 to exit) -> ");
			choice = sc.nextInt();
			sc.nextLine();

			switch (choice) {
			case 1: {
				int innerChoice = -1;
				while (innerChoice != 0) {
					System.out.println("\n***** STUDENT MANAGEMENT *****");
					System.out.print("1. Add a New Student" + "\n2. Remove a Student" + "\n3. Search Student Details"
							+ "\n4. Show All Students" + "\n\nEnter your choice (0 to go back) -> ");
					innerChoice = sc.nextInt();
					sc.nextLine();

					switch (innerChoice) {
					case 1:
						System.out.print("\nEnter student name -> ");
						String name = sc.nextLine();
						System.out.print("Enter student email -> ");
						String email = sc.nextLine();
						System.out.print("Enter student department -> ");
						String department = sc.nextLine();
						System.out.println(studentService.addStudent(name, email, department));
						break;
					case 2:
						System.out.print("\nEnter student email -> ");
						email = sc.nextLine();
						System.out.println(studentService.removeStudent(email));
						break;
					case 3:
						System.out.print("\nEnter student email -> ");
						email = sc.nextLine();
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
				int innerChoice = -1;
				while (innerChoice != 0) {
					System.out.println("\n***** COURSE MANAGEMENT *****");
					System.out.print("1. Add a New Course" + "\n2. Remove a Course" + "\n3. Search Course Details"
							+ "\n4. Show All Courses" + "\n\nEnter your choice (0 to go back) -> ");
					innerChoice = sc.nextInt();
					sc.nextLine();

					switch (innerChoice) {
					case 1:
						System.out.print("\nEnter course code -> ");
						String code = sc.nextLine();
						System.out.print("Enter course name -> ");
						String name = sc.nextLine();
						System.out.print("Enter instructor name -> ");
						String instructorName = sc.nextLine();
						System.out.println(courseService.addCourse(code, name, instructorName));
						break;
					case 2:
						System.out.print("\nEnter course code -> ");
						code = sc.nextLine();
						System.out.println(courseService.removeCourse(code));
						break;
					case 3:
						System.out.print("\nEnter course code -> ");
						code = sc.nextLine();
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
				}
				break;
			}
			case 3: {
				int innerChoice = -1;
				while (innerChoice != 0) {
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
				}
				break;
			}
			case 4: {
				int innerChoice = -1;
				while (innerChoice != 0) {
					System.out.println("\n***** REPORTING *****");
					System.out.print("1. Generate Enrollment Report" + "\n\nEnter your choice (0 to go back) -> ");
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
				}
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
