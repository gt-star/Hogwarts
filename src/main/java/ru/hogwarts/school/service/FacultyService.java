package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.exception.FacultyNotFoundException;
import ru.hogwarts.school.exception.StudentNotFoundException;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class FacultyService {
    private final Map<Long, Faculty> facultyMap;
    private long studentId = 1;

    public FacultyService() {
        facultyMap = new HashMap<>();
    }
    public Faculty createFaculty(Faculty faculty){
        facultyMap.put(studentId,faculty);
        studentId++;
        return faculty;
    }
    public Faculty getFaculty(long id){
        if (!facultyMap.containsKey(id)){
            throw new FacultyNotFoundException(id);
        }
        return facultyMap.get(id);
    }
    public Faculty updateFaculty(long id, Faculty newFaculty){
        Faculty oldsFaculty = getFaculty(id);
        oldsFaculty.setName(newFaculty.getName());
        oldsFaculty.setColor(newFaculty.getColor());
        facultyMap.put(id,oldsFaculty);
        return newFaculty;
    }
    public Faculty deleteFaculty(long id){
        if (!facultyMap.containsKey(id)){
            throw new FacultyNotFoundException(id);
        }
        return facultyMap.remove(id);
    }

    public Collection<Faculty> findByColor(String color){
        return facultyMap.values().stream()
                .filter(faculty -> faculty.getColor().equals(color))
                .collect(Collectors.toList());
    }
}
