package org.gravitytwog.repositories;

import org.gravitytwog.dto.GetPageDTO;
import org.gravitytwog.dto.PageDTO;
import org.gravitytwog.entities.Student;
import org.gravitytwog.interfaces.StudentsRepository;

public class StudentsRepositoryImpl implements StudentsRepository {
    @Override
    public void addStudent(Student student) {

    }

    @Override
    public PageDTO<Student> getStudents(GetPageDTO dto) {
        return null;
    }

    @Override
    public void deleteStudent(int studentId) {

    }
}
