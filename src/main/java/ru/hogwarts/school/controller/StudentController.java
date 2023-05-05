package ru.hogwarts.school.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.service.AvatarService;
import ru.hogwarts.school.service.StudentService;

import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;

@RestController
@RequestMapping("/student")
public class StudentController {
    private final StudentService studentService;
    private final AvatarService avatarService;

    public StudentController(StudentService studentService, AvatarService avatarService) {
        this.studentService = studentService;
        this.avatarService = avatarService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudentInfo(@PathVariable Long id) {
        Student student = studentService.findStudent(id);
        if (student == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(student);
    }

    @GetMapping("/find-by-age")
    public ResponseEntity<Collection<Student>> findStudents(@RequestParam(required = false) Integer age) {
        if (age > 0) {
            return ResponseEntity.ok(studentService.findByAge(age));
        }
            return ResponseEntity.ok(studentService.getAllStudents());
        }

    @GetMapping("/age-between-min-max")
    public ResponseEntity<Collection<Student>> findStudentsByAgeBetween(@RequestParam (required = false) Integer min,
                                                            @RequestParam (required = false) Integer max) {
        return ResponseEntity.ok(studentService.findByAgeBetweenMinMax(min,max));
        }

    @PostMapping
    public Student addStudent(@RequestBody Student student) {
        return studentService.createStudent(student);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Student> editStudent(@RequestBody Student student, @PathVariable Long id) {
        Student foundStudent = studentService.editStudent(student);
        if (foundStudent == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(student);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Student> deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/studentStartWitch")
    public ResponseEntity<List<String>> getAllStudentStartWitchLetter(@RequestParam String letter) {
        return ResponseEntity.ok(studentService.getAllStudentStartWitchLetter(letter));
    }

    @GetMapping("/studentAverAgeAgeOfAllStudents")
    public ResponseEntity<Double>getAverageAgeOfAllStudents(){
        return ResponseEntity.ok(studentService.getAverageAgeOfAllStudents());
    }

    @GetMapping("/returnInteger")
    Integer returnInteger() {
        return Stream.iterate(1, a -> a + 1).limit(1_000_000)
                .parallel().reduce(0, (a, b) -> a + b);
    }
    @GetMapping("/printAllStudentSynchronized")
    public void printAllStudentSynchronizedMethod(){
        studentService.printAllStudentSynchronizedMethod();
    }

    @GetMapping("/printAllStudentParallel")
    public void printAllStudentParallelMethod(){
        studentService.printAllStudentParallelMethod();
    }
}
