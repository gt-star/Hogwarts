package ru.hogwarts.school.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.hogwarts.school.model.Avatar;
import ru.hogwarts.school.model.Student;

import java.util.Collection;

public interface AvatarRepository extends JpaRepository<Student,Long> {
    Collection<Student> save(Avatar avatar);
}
