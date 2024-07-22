package org.gravitytwog.interfaces;

import org.gravitytwog.dto.GetPageDTO;
import org.gravitytwog.dto.PageDTO;
import org.gravitytwog.entities.Student;

public interface StudentsRepository {
    void addStudent(Student student);

    PageDTO<Student> getStudents(GetPageDTO dto);

    void deleteStudent(int studentId);
}
