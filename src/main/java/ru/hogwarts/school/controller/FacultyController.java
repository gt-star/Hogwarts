package ru.hogwarts.school.controller;

import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.service.FacultyService;

import java.util.Collection;

@RequestMapping("/faculties")
@RestController
public class FacultyController {
    private final FacultyService facultyService;
    public FacultyController(FacultyService facultyService) {
        this.facultyService = facultyService;
    }
    @PostMapping
    public Faculty createStudent(@RequestBody Faculty faculty){
        return facultyService.createFaculty(faculty);
    }
    @GetMapping("/{id}")
    public Faculty getFaculty(@PathVariable long id){
        return facultyService.getFaculty(id);
    }
    @PutMapping("/{id}")
    public Faculty updateFaculty(@PathVariable long id,
                                 @RequestBody Faculty faculty){
        return facultyService.updateFaculty(id,faculty);
    }
    @DeleteMapping("/{id}")
    public Faculty deleteFaculty(@PathVariable long id){
        return facultyService.deleteFaculty(id);
    }

    @GetMapping("{id}/students")
    public Collection<Student> getStudentsOfFaculty(@PathVariable("id") Long id) {
        return facultyService.getStudentsByFaculty(id);
    }
    @GetMapping("/facultyOfNameOrColor")
    public Collection<Faculty> findByNameOrColorContainsIgnoreCase(@RequestParam(required = true) String nameOrColor){
        return facultyService.findAllByColorContainsIgnoreCaseOrNameContainsIgnoreCase(nameOrColor);
    }
}
