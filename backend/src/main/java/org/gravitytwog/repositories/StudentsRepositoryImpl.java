package org.gravitytwog.repositories;

import org.gravitytwog.dto.GetPageDTO;
import org.gravitytwog.dto.PageDTO;
import org.gravitytwog.dto.StudentDTO;
import org.gravitytwog.entities.Student;
import org.gravitytwog.interfaces.StudentsRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;

public class StudentsRepositoryImpl implements StudentsRepository {
    private final Connection conn;

    public StudentsRepositoryImpl(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void addStudent(Student student) {
        try {
            var dto = student.toDTO();

            PreparedStatement statement = this.conn.prepareStatement("""
                INSERT INTO students(name, last_name, patronymic, birth_date, "group")
                VALUES(?, ?, ?, ?, ?) RETURNING id;
            """);

            statement.setString(1, dto.name);
            statement.setString(2, dto.lastName);
            statement.setString(3, dto.patronymic);
            statement.setObject(4, dto.birthDate);
            statement.setString(5, dto.group);

            ResultSet resultSet = statement.executeQuery();

            if (!resultSet.next()) {
                throw new Exception("Unable to add student");
            }
            int id = resultSet.getInt(1);
            student.setId(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public PageDTO<Student> getStudents(GetPageDTO dto) {
        try {
            PreparedStatement countStatement = this.conn.prepareStatement("""
                SELECT COUNT(*) FROM students;
            """);

            ResultSet countResult = countStatement.executeQuery();

            if (!countResult.next()) {
                throw new Exception("Unable to get students");
            }

            int totalCount = countResult.getInt(1);
            var students = new ArrayList<Student>();

            PreparedStatement statement = this.conn.prepareStatement("""
                SELECT id, name, last_name, patronymic, birth_date, "group"
                FROM students LIMIT ? OFFSET ?;
            """);

            statement.setInt(1, dto.pageSize);
            statement.setInt(2, dto.pageSize * (dto.pageNumber - 1));

            ResultSet studentsResult = statement.executeQuery();

            while (studentsResult.next()) {
                var studentDTO = new StudentDTO();

                studentDTO.id = studentsResult.getInt(1);
                studentDTO.name = studentsResult.getString(2);
                studentDTO.lastName = studentsResult.getString(3);
                studentDTO.patronymic = studentsResult.getString(4);
                studentDTO.birthDate = studentsResult.getObject(5, LocalDate.class);
                studentDTO.group = studentsResult.getString(6);

                students.add(Student.fromDTO(studentDTO));
            }

            return new PageDTO<>(
                students,
                totalCount
            );
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteStudent(int studentId) {
        try {
            PreparedStatement statement = this.conn.prepareStatement("""
                DELETE FROM students WHERE id = ?;
            """);

            statement.setInt(1, studentId);

            int deleted = statement.executeUpdate();
            if (deleted <= 0) {
                throw new Exception("Unable to delete student");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
