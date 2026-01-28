package org.example.dao;

import org.example.entity.Student;
import java.util.List;


public interface StudentDAO {
    // Register / Save student
    void saveStudent(Student student);

    void remove(String rollNo);

    // Update password with validation
//    void updatePassword(int rollNo, String newPassword);

//    Student getStudent(int rollNo);
    Student getStudent(String rollNo);

    // Fetch student using HQL by email
    Student getStudentByEmail(String email);

    // Login validation using email + password
    Student login(String email, String password);

    // Update password (encrypted)
    void updatePassword(String rollNo, String newPassword);

    // Fetch all students
    List<Student> getAllStudents();
}
