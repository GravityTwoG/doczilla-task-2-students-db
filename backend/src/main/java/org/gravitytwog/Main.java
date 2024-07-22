package org.gravitytwog;

import org.gravitytwog.dto.GetPageDTO;
import org.gravitytwog.entities.Student;
import org.gravitytwog.repositories.StudentsRepositoryImpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        try {
            Connection conn = DriverManager.getConnection(
                "jdbc:postgresql://127.0.0.1:5432/students-db",
                "user",
                "password"
            );

            conn.prepareStatement("""
                CREATE TABLE IF NOT EXISTS students(
                    id SERIAL PRIMARY KEY,
                    name text NOT NULL,
                    last_name text NOT NULL,
                    patronymic text NOT NULL,
                    birth_date date NOT NULL,
                    "group" text NOT NULL
                );
            """).execute();

            var repo = new StudentsRepositoryImpl(conn);

            var student = new Student(
                "A",
                "B",
                "C",
                LocalDate.now(),
                "A-01"
            );

            repo.addStudent(
                student
            );

            var students = repo.getStudents(new GetPageDTO(1, 2));

            System.out.println("Total count: " + students.totalCount);
            students.items.forEach(s -> System.out.println(s.toDTO().id + " " + s.toDTO().name));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}