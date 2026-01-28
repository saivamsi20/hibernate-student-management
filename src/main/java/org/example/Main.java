package org.example;

import org.example.dao.StudentDAO;
import org.example.dao.StudentDAOImpl;
import org.example.entity.Student;
import org.example.exception.StudentNotFoundException;

import java.util.List;
import java.util.Scanner;

//import static org.example.dao.StudentDAOImpl.getStudent;
//import static org.example.dao.StudentDAOImpl.remove;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.

public class Main {

    private static final String ROLL_PATTERN = "\\d{2}D\\d{2}A\\d{4}";

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        StudentDAO dao = new StudentDAOImpl();

        while (true) {

            System.out.println("\n=== Student MENU ===");
            System.out.println("1. Register Student");
            System.out.println("2. Update Student password");
            System.out.println("3. Delete Student");
            System.out.println("4. Find Student (by Roll No)");
            System.out.println("5. Find All Student");
            System.out.println("6. Login");
            System.out.println("7. Search Student by Email");
            System.out.println("8. Exit");

            System.out.print("\nEnter your choice: ");
            int choice;

            try {
                choice = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid choice. Enter a number.");
                continue;
            }

            switch (choice) {

                // ================= REGISTER =================
                case 1 -> {
                    String rollNo;

                    while (true) {
                        System.out.print("Enter Roll No (e.g., 23D41A0401): ");
                        rollNo = sc.nextLine().trim();

                        if (!rollNo.matches(ROLL_PATTERN)) {
                            System.out.println("Invalid roll number format.");
                            continue;
                        }
                        break;
                    }

                    System.out.print("Enter Full Name: ");
                    String name = sc.nextLine();

                    System.out.print("Enter Email ID: ");
                    String email = sc.nextLine();

                    System.out.print("Enter Password: ");
                    String password = sc.nextLine();

                    dao.saveStudent(new Student(rollNo, name, email, password));
                    System.out.println("Student added successfully");
                }

                // ================= UPDATE PASSWORD =================
                case 2 -> {
                    System.out.print("Enter Roll No: ");
                    String rollNo = sc.nextLine();

                    System.out.print("Enter New Password: ");
                    String newPassword = sc.nextLine();

                    try {
                        dao.updatePassword(rollNo, newPassword);
                        System.out.println("Password updated successfully");
                    } catch (StudentNotFoundException e) {
                        System.out.println(e.getMessage());
                    }
                }

                // ================= DELETE =================
                case 3 -> {
                    System.out.print("Enter Roll No: ");
                    String rollNo = sc.nextLine();

                    try {
                        dao.remove(rollNo);
                        System.out.println("Student deleted successfully");
                    } catch (StudentNotFoundException e) {
                        System.out.println(e.getMessage());
                    }
                }

                // ================= FIND BY ROLL NO =================
                case 4 -> {
                    System.out.print("Enter Roll No: ");
                    String rollNo = sc.nextLine();

                    Student student = dao.getStudent(rollNo);

                    if (student != null) {
                        System.out.println("\nStudent found");
                        System.out.println("Roll No : " + student.getRollNo());
                        System.out.println("Name    : " + student.getFullName());
                        System.out.println("Email   : " + student.getEmail());
                    } else {
                        System.out.println("Student not found");
                    }
                }

                // ================= FIND ALL =================
                case 5 -> {
                    List<Student> students = dao.getAllStudents();

                    if (students.isEmpty()) {
                        System.out.println("No students found");
                    } else {
                        System.out.println("\n----- Student List -----");
                        for (Student s : students) {
                            System.out.println(
                                    "Roll No : " + s.getRollNo() +
                                            ", Name : " + s.getFullName() +
                                            ", Email : " + s.getEmail()
                            );
                        }
                        System.out.println("------------------------");
                    }
                }

                // ================= LOGIN =================
                case 6 -> {
                    System.out.print("Enter Email: ");
                    String email = sc.nextLine();

                    int attempts = 3;

                    while (attempts > 0) {
                        System.out.print("Enter Password (or type EXIT to quit): ");
                        String password = sc.nextLine();

                        if (password.equalsIgnoreCase("EXIT")) {
                            System.out.println("Login cancelled");
                            break;
                        }

                        Student loggedIn = dao.login(email, password);

                        if (loggedIn != null) {
                            System.out.println("Login successful");
                            System.out.println("Welcome " + loggedIn.getFullName());
                            break;
                        } else {
                            attempts--;
                            if (attempts > 0) {
                                System.out.println(
                                        "Invalid password. Attempts left: " + attempts
                                );
                            } else {
                                System.out.println("Too many failed attempts. Login blocked.");
                            }
                        }
                    }
                }

                // ================= SEARCH BY EMAIL =================
                case 7 -> {
                    System.out.print("Enter Email: ");
                    String email = sc.nextLine();

                    Student student = dao.getStudentByEmail(email);

                    if (student != null) {
                        System.out.println("\nStudent found");
                        System.out.println("Roll No : " + student.getRollNo());
                        System.out.println("Name    : " + student.getFullName());
                        System.out.println("Email   : " + student.getEmail());
                    } else {
                        System.out.println("Student not found");
                    }
                }

                // ================= EXIT =================
                case 8 -> {
                    System.out.println("Exiting application...");
                    sc.close();
                    System.exit(0);
                }

                default -> System.out.println("Invalid choice");
            }
        }
    }
}


//1. Add Student
//2. View Student by Roll No
//3. Update Student Password
//4. View All Students
//5. Exit
