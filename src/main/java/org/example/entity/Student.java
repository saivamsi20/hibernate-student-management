package org.example.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Student {
    @Id
    @Column(length=10, name = "Roll no")
    private String rollNo;
    @Column(name = "Name")
    private String fullName;
    @Column(name = "Email_ID")
    private String email;
    private String password;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRollNo() {
        return rollNo;
    }

    public void setRollNo(String rollNo) {
        this.rollNo = rollNo;
    }

    Student (){
    }
    public Student(String rollNo, String fullName, String email, String password) {
        this.rollNo = rollNo;
        this.fullName = fullName;
        this.email = email;
        this.password = password;
    }
    @Override
    public String toString() {
        return "Student{" +
                "email='" + email + '\'' +
                ", rollNo='" + rollNo + '\'' +
                ", fullName='" + fullName + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
