package ru.hogwarts.school.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.AmountStudents;
import ru.hogwarts.school.model.AverageAgeStudent;
import ru.hogwarts.school.model.LastValues;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repositories.StudentRepository;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentService {
    Logger logger = LoggerFactory.getLogger(StudentService.class);

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student createStudent(Student student) {
        logger.info("Was invoked method for create student");
        return studentRepository.save(student);
    }

    public Student findStudent(Long id) {
        logger.info("Was invoked method for find student");
        return studentRepository.findById(id).get();
    }

    public Student editStudent(Student student) {
        logger.info("Was invoked method for edit student");
        return studentRepository.save(student);

    }

    public void deleteStudent(Long id) {
        logger.info("Was invoked method for delete student");
        studentRepository.deleteById(id);
    }

    public Collection<Student> getAllStudents() {
        logger.info("Was invoked method for get all students");
        return studentRepository.findAll();
    }

    public Collection<Student> findByAge(int age) {
        logger.info("Was invoked method for find student by age");
        return studentRepository.findByAge(age);
    }

    public Collection<Student> findByAgeBetweenMinMax (int min, int max) {
        logger.info("Was invoked method for find student by age between min and max");
        return studentRepository.findByAgeBetween(min,max);
    }

    public List<AmountStudents> getAmountStudents () {
        logger.info("Was invoked method for get amount student");
        return studentRepository.getAmountStudents();
    }
    public List<AverageAgeStudent> getAverageAgeStudent () {
        logger.info("Was invoked method for get average age by students");
        return studentRepository.getAverageAgeStudent();
    }

    public List<LastValues> get5LastValues () {
        logger.info("Was invoked method for get 5 last values");
        return studentRepository.get5LastValues();
    }

    public List<String> getAllStudentStartWitchLetter(String letter) {
        logger.info("Method was called getAllStudentStartWitchLetter ");
        return studentRepository.findAll()
                .parallelStream()
                .filter(student -> student.getName().startsWith(letter.toUpperCase()))
                .map(student -> student.getName().toUpperCase())
                .collect(Collectors.toList());
    }

    public double getAverageAgeOfAllStudents() {
        logger.info("Method was called getAverageAgeOfAllStudents");
        return studentRepository.findAll()
                .stream()
                .mapToInt(Student::getAge)
                .average()
                .orElse(0);
    }
    public void printStudentSynchronized(Student student, Student student2) {
        System.out.println(student.getName());
        System.out.println(student2.getName());
    }

    public synchronized void printAllStudentSynchronizedMethod() {
        List<Student> students = studentRepository.findAll();
        printStudentSynchronized(students.get(1), students.get(2));

        new Thread(() -> {
            printStudentSynchronized(students.get(3), students.get(4));
        }).start();

        new Thread(() -> {
            printStudentSynchronized(students.get(5), students.get(6));
        }).start();

    }

    public void printAllStudentParallelMethod() {

        List<Student> students = studentRepository.findAll();
        printStudentParallel(students.get(0));
        printStudentParallel(students.get(1));

        new Thread(() -> {
            printStudentParallel(students.get(3));
            printStudentParallel(students.get(4));
        }).start();

        new Thread(() -> {
            printStudentParallel(students.get(5));
            printStudentParallel(students.get(6));
        }).start();

    }

    public void printStudentParallel(Student student) {
        System.out.println(student.getName());

    }
}

