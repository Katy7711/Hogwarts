package ru.hogwarts.school.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.service.StudentService;

import java.util.Collection;
import java.util.Collections;

@RestController
@RequestMapping("/student")
public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudentInfo(@PathVariable long id) {
        Student student = studentService.findStudent(id);
        if (student == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(student);
    }

//    @GetMapping
//    public ResponseEntity<Collection<Student>> getAllStudents() {
//        return ResponseEntity.ok(studentService.getAllStudents());
//    }

    @GetMapping
    public ResponseEntity<Collection<Student>> findStudents (@RequestParam(required = false) int age) {
        if (age > 0) {
            return ResponseEntity.ok(studentService.findByAge(age));
        }
        return ResponseEntity.ok(Collections.emptyList());
    }

    @PostMapping
    public Student addStudent (@RequestBody Student student) {
        return studentService.createStudent(student);
    }
    @PutMapping
    public ResponseEntity<Student> editStudent (@RequestBody Student student) {
        Student foundStudent = studentService.editStudent(student);
        if (foundStudent == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(student);
    }
    @DeleteMapping("/{id}")
    public Student deleteStudent (@PathVariable long id) {
        return studentService.deleteStudent(id);
    }

}
