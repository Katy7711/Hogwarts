package ru.hogwarts.school.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.hogwarts.school.model.AmountStudents;
import ru.hogwarts.school.model.AverageAgeStudent;
import ru.hogwarts.school.model.LastValues;
import ru.hogwarts.school.model.Student;

import java.util.Collection;
import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {

    Collection<Student> findByAge(int age);

    Collection<Student> findByAgeBetween(int min, int max);

    List<Student> findAll();

    @Query (value = "select count(*) from student", nativeQuery = true)
    List<AmountStudents> getAmountStudents ();

    @Query (value = "select avg(age) from student", nativeQuery = true)
    List<AverageAgeStudent> getAverageAgeStudent ();

    @Query (value = "select from student order by id desc limit 5", nativeQuery = true)
    List<LastValues> get5LastValues ();




}
