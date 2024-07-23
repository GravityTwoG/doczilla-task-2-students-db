package org.gravitytwog.controllers;

import io.swagger.v3.oas.annotations.Operation;
import org.gravitytwog.dto.AddStudentDTO;
import org.gravitytwog.dto.GetPageDTO;
import org.gravitytwog.dto.PageDTO;
import org.gravitytwog.dto.StudentDTO;
import org.gravitytwog.entities.Student;
import org.gravitytwog.exceptions.EntityNotFoundException;
import org.gravitytwog.services.StudentsService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/students")
public class StudentsController {
    private final StudentsService studentsService;

    public StudentsController(StudentsService studentsService) {
        this.studentsService = studentsService;
    }

    @Operation(summary = "Add new student")
    @PostMapping
    public ResponseEntity<StudentDTO> addStudent(@RequestBody AddStudentDTO dto) {
        var student = this.studentsService.addStudent(dto);

        return ResponseEntity.status(HttpStatus.OK).body(student.toDTO());
    }

    @Operation(summary = "Get students")
    @GetMapping
    public ResponseEntity<PageDTO<StudentDTO>> getStudents(GetPageDTO dto) {
        var students = this.studentsService.getStudents(dto);

        var studentsDTO = new PageDTO<>(
            students.items.stream().map(Student::toDTO).toList(),
            students.totalCount
        );

        return ResponseEntity.ok(studentsDTO);
    }

    @Operation(summary = "Delete student")
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteStudents(
            @PathVariable(name = "id") int studentId
    ) throws EntityNotFoundException {
        this.studentsService.deleteStudent(studentId);

        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
