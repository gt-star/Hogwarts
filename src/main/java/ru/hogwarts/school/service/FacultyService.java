package ru.hogwarts.school.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    Logger logger = LoggerFactory.getLogger(FacultyService.class);
    public FacultyService(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }
    public Faculty createFaculty(Faculty faculty){
        logger.info("Method called createFaculty");
        faculty.setId(null);
        return facultyRepository.save(faculty);
    }
    public Faculty getFaculty(long id){
        logger.info("Method called getFaculty");
        return facultyRepository.findById(id).orElseThrow(()-> {
            logger.error("There is not student with id = " + id);
            return new FacultyNotFoundException(id);
        });
    }
    public Faculty updateFaculty(long id, Faculty newFaculty){
        logger.info("Method called updateFaculty");
        Faculty oldsFaculty = getFaculty(id);
        oldsFaculty.setName(newFaculty.getName());
        oldsFaculty.setColor(newFaculty.getColor());
        return facultyRepository.save(oldsFaculty);
    }
    public Faculty deleteFaculty(long id){
        logger.info("Method called deleteFaculty");
        Faculty faculty = getFaculty(id);
        facultyRepository.delete(faculty);
        return faculty;
    }
    public Collection<Faculty> findAllByColorContainsIgnoreCaseOrNameContainsIgnoreCase(String nameOrColor){
        logger.info("Method called findAllByColorContainsIgnoreCaseOrNameContainsIgnoreCase");
        return facultyRepository.findAllByColorContainsIgnoreCaseOrNameContainsIgnoreCase(nameOrColor,nameOrColor);
    }

    public Collection<Student> getStudentsByFaculty(long id) {
        logger.info("Method called getStudentsByFaculty");
        return facultyRepository.findById(id)
                .map(Faculty::getStudents)
                .orElseGet(Collections::emptyList);
    }

}
