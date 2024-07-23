package org.gravitytwog.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public class AddStudentDTO {
    @NotNull
    @Size(min = 1, max = 1024)
    public String name;

    @NotNull
    @Size(min = 1, max = 1024)
    public String lastName;

    @NotNull
    @Size(min = 1, max = 1024)
    public String patronymic;

    @NotNull
    @Past
    public LocalDate birthDate;

    @NotNull
    @Size(min = 1, max = 16)
    public String group;

    public AddStudentDTO() {

    }
}

