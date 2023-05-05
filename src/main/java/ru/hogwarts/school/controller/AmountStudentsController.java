package ru.hogwarts.school.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.hogwarts.school.model.AmountStudents;
import ru.hogwarts.school.model.AverageAgeStudent;
import ru.hogwarts.school.model.LastValues;
import ru.hogwarts.school.service.StudentService;

import java.util.List;

@RestController
public class AmountStudentsController {

    private final StudentService studentService;

    public AmountStudentsController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping ("/amount-students")
    public List<AmountStudents> getAmountStudents (){
        return studentService.getAmountStudents();
    }

    @GetMapping ("/average-age-students")
    public List<AverageAgeStudent> getAverageAgeStudent (){
        return studentService.getAverageAgeStudent();
    }

    @GetMapping ("/last-5-values")
    public List<LastValues> get5LastValues (){
        return studentService.get5LastValues();
    }
}
