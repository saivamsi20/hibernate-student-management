package org.example.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
    private static final SessionFactory sessionFactory;
    static {
        try {
            sessionFactory = new Configuration()
                    .configure("hibernate.cfg.xml").addAnnotatedClass(org.example.entity.Student.class)   // loads hibernate.cfg.xml from classpath
                    .buildSessionFactory();

            System.out.println("SessionFactory created successfully");

        } catch (Throwable ex) {
            System.err.println("❌ Initial SessionFactory creation failed");
            ex.printStackTrace();   // 🔴 THIS IS CRITICAL
            throw new ExceptionInInitializerError(ex);
        }
    }
    private HibernateUtil() {}
    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}