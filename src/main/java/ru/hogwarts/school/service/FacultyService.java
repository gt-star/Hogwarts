package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.exception.FacultyNotFoundException;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.FacultyRepository;

import java.util.Collection;
import java.util.Collections;

@Service
public class FacultyService {
    private final FacultyRepository facultyRepository;

    public FacultyService(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }
    public Faculty createFaculty(Faculty faculty){
        faculty.setId(null);
        return facultyRepository.save(faculty);
    }
    public Faculty getFaculty(long id){
        return facultyRepository.findById(id).orElseThrow(()-> new FacultyNotFoundException(id));
    }
    public Faculty updateFaculty(long id, Faculty newFaculty){
        Faculty oldsFaculty = getFaculty(id);
        oldsFaculty.setName(newFaculty.getName());
        oldsFaculty.setColor(newFaculty.getColor());
        return facultyRepository.save(oldsFaculty);
    }
    public Faculty deleteFaculty(long id){
        Faculty faculty = getFaculty(id);
        facultyRepository.delete(faculty);
        return faculty;
    }
    public Faculty findAllByColorContainsIgnoreCaseOrNameContainsIgnoreCase(String color, String name){
        return facultyRepository.findAllByColorContainsIgnoreCaseOrNameContainsIgnoreCase(color,name);
    }

    public Collection<Student> getStudentsByFaculty(long id) {
        Collection<Student> students = facultyRepository.findById(id)
                .map(faculty -> faculty.getStudents())
                .orElseGet(Collections::emptyList);
        return students;
    }

}
