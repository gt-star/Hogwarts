package ru.hogwarts.school.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.hogwarts.school.model.Student;

import java.util.Collection;

public interface StudentRepository extends JpaRepository<Student,Long> {
    Collection<Student> findByAgeBetween(Integer min, Integer max);
    @Query(value = "SELECT COUNT(*) FROM students", nativeQuery = true)
    Integer getAllByCountStudent();
    @Query(value = "SELECT AVG(age) FROM students", nativeQuery = true)
    Double getAverageAge();
    @Query(value = "SELECT * FROM students ORDER BY id DESC LIMIT 5", nativeQuery = true)
    Collection<Student> getLastOfFiveStudents();

}
