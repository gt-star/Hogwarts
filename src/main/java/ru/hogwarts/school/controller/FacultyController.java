package ru.hogwarts.school.controller;

import org.springframework.http.ResponseEntity;
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
    public Faculty getStudent(@PathVariable long id){
        return facultyService.getFaculty(id);
    }
    @PutMapping("/{id}")
    public Faculty updateStudent(@PathVariable long id,
                                 @RequestBody Faculty faculty){
        return facultyService.updateFaculty(id,faculty);
    }
    @DeleteMapping("/{id}")
    public Faculty deleteStudent(@PathVariable long id){
        return facultyService.deleteFaculty(id);
    }
    @GetMapping()
    public Collection<Faculty> findByColor (@RequestParam String color){
        return facultyService.findByColor(color);
    }

}
