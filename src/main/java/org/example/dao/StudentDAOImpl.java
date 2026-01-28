package org.example.dao;


import org.example.entity.Student;
import org.example.exception.StudentNotFoundException;
import org.example.util.HibernateUtil;
import org.example.util.PasswordUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class StudentDAOImpl implements StudentDAO {

    // Save student (password encrypted)
    @Override
    public void saveStudent(Student student) {

        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();

            // Encrypt password before saving
            String encryptedPassword = PasswordUtil.hashPassword(student.getPassword());
            student.setPassword(encryptedPassword);

            session.persist(student);

            tx.commit();
        }catch (Exception e){
            if(tx != null){
                tx.rollback();
            }
        }
        finally {
            session.close();
        }
    }

    // Fetch student by email using HQL
    @Override
    public Student getStudentByEmail(String email) {

        Session session = HibernateUtil.getSessionFactory().openSession();

        String hql = "from Student where email = :email";
        Query<Student> query = session.createQuery(hql, Student.class);
        query.setParameter("email", email);

        Student student = query.uniqueResult();
        session.close();

        return student;
    }

    // Login validation using encrypted password
    @Override
    public Student login(String email, String password) {

        Session session = HibernateUtil.getSessionFactory().openSession();

        String encryptedPassword = PasswordUtil.hashPassword(password);

        String hql = "from Student where email = :email and password = :password";
        Query<Student> query = session.createQuery(hql, Student.class);
        query.setParameter("email", email);
        query.setParameter("password", encryptedPassword);

        Student student = query.uniqueResult();
        session.close();


        return student;
    }

    // Update password with validation
    @Override
    public void updatePassword(String rollNo, String newPassword) {

        if (newPassword == null || newPassword.length() < 4) {
            throw new IllegalArgumentException("Password must be at least 4 characters long");
        }

        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();

        Student student = session.find(Student.class, rollNo);

        if (student == null) {
            tx.rollback();
            session.close();
            throw new StudentNotFoundException("Student not found with roll no: " + rollNo);
        }

        String encryptedPassword = PasswordUtil.hashPassword(newPassword);
        student.setPassword(encryptedPassword);

        session.merge(student);

        tx.commit();
        session.close();
    }

    @Override
    public Student getStudent(String rollNo) {
        Session s = HibernateUtil.getSessionFactory().openSession();
        Student student = s.find(Student.class, rollNo);
        s.close();
        return student;
    }


    @Override
    public void remove(String rollNo) {
        Session s = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = s.beginTransaction();
        Student student = s.find(Student.class, rollNo);
//        s.remove(student);
        if(student == null){
            tx.rollback();
            s.close();
            throw new StudentNotFoundException("Student not there with roll no: " + rollNo);
        }
        s.remove(student);
        tx.commit();
        s.close();
    }

    // Fetch all students
    @Override
    public List<Student> getAllStudents(){
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Student> student = session.createQuery("from Student", Student.class).list();
        session.close();
        return student;
    }
}


