# Hibernate Student Management System

A menu-driven Java console application built using Hibernate ORM and PostgreSQL.
This project demonstrates basic CRUD operations with a clean DAO-based structure.

---

## Features

- Add student details (roll no, name, email, password)
- View student by roll number
- Update student password
- View all students
- Menu-driven console interface
- Uses Hibernate ORM with PostgreSQL

---

## Technologies Used

- Java
- Hibernate ORM
- PostgreSQL
- Maven
- JDBC

---

## Project Structure

```text
src/main/java
├── entity
│   └── Student.java
├── util
│   └── HibernateUtil.java
├── StudentDAO.java
└── App.java
```

---

## Database Schema

```sql
CREATE TABLE student (
    roll_no INT PRIMARY KEY,
    name VARCHAR(50),
    email VARCHAR(50),
    password VARCHAR(50)
);
```

---

## How to Run

1. Clone the repository:
```bash
git clone https://github.com/<your-username>/hibernate-student-management.git
```

2. Configure PostgreSQL details in `hibernate.cfg.xml`

3. Run `App.java`

---

## Sample Menu

```
1. Add Student
2. View Student by Roll No
3. Update Student Password
4. View All Students
5. Exit
```

---

## Learning Outcomes

- Understanding Hibernate ORM flow
- Entity mapping using annotations
- Session and Transaction management
- DAO design pattern
- CRUD operations using Hibernate

---

## Future Enhancements

- Password encryption
- Login authentication
- Spring Boot integration
- REST API version
- Validation and exception handling

---

## Author

Sai Vamsi  
Java Backend Developer
