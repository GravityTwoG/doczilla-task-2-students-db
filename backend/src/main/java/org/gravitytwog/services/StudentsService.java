package org.gravitytwog.services;

import org.gravitytwog.dto.AddStudentDTO;
import org.gravitytwog.dto.GetPageDTO;
import org.gravitytwog.dto.PageDTO;
import org.gravitytwog.entities.Student;
import org.gravitytwog.exceptions.EntityNotFoundException;
import org.gravitytwog.interfaces.StudentsRepository;

import org.springframework.stereotype.Service;

@Service
public class StudentsService {
    private final StudentsRepository repo;

    public StudentsService(StudentsRepository repo) {
        this.repo = repo;
    }

    public Student addStudent(AddStudentDTO dto) {
        var student = new Student(
                dto.name,
                dto.lastName,
                dto.patronymic,
                dto.birthDate,
                dto.group
        );

        this.repo.addStudent(student);

        return student;
    }

    public PageDTO<Student> getStudents(GetPageDTO dto) {
        return this.repo.getStudents(dto);
    }

    public void deleteStudent(int studentId) throws EntityNotFoundException {
        this.repo.deleteStudent(studentId);
    }
}
