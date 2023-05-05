package ru.hogwarts.school.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.hogwarts.school.model.Faculty;

import java.util.Collection;
import java.util.List;

public interface FacultyRepository extends JpaRepository<Faculty, Long> {
    Collection <Faculty> findByColorContainsIgnoreCase(String color);

    List<Faculty> findAll();

    Collection <Faculty> findByNameContainsIgnoreCase(String Name);
}
