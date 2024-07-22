package org.gravitytwog.entities;

import org.gravitytwog.dto.StudentDTO;

import java.time.LocalDate;

public class Student {
    private int id;

    private final String name;

    private final String lastName;

    private final String patronymic;

    private final LocalDate birthDate;

    private final String group;

    public Student(
            String name,
            String lastName,
            String patronymic,
            LocalDate birthDate,
            String group
    ) {
        this.name = name;
        this.lastName = lastName;
        this.patronymic = patronymic;
        this.birthDate = birthDate;

        this.group = group;
    }

    public void setId(int id) {
        this.id = id;
    }

    public StudentDTO toDTO() {
        var dto = new StudentDTO();

        dto.id = this.id;

        dto.name = this.name;
        dto.lastName = this.lastName;
        dto.patronymic = this.patronymic;
        dto.birthDate = this.birthDate;

        dto.group = this.group;

        return dto;
    }

    public static Student fromDTO(StudentDTO dto) {
        var student =  new Student(
                dto.name,
                dto.lastName,
                dto.patronymic,
                dto.birthDate,
                dto.group
        );
        student.id = dto.id;

        return student;
    }
}
