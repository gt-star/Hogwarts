package ru.hogwarts.school.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.service.StudentService;

import java.util.Collection;

@RequestMapping("/students")
@RestController
public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }
    @PostMapping
    public Student createStudent(@RequestBody Student student){
        return studentService.createStudent(student);
    }
    @GetMapping("/{id}")
    public Student getStudent(@PathVariable long id){
        return studentService.getStudent(id);
    }
    @PutMapping("/{id}")
    public Student updateStudent(@PathVariable long id,
                                 @RequestBody Student student){
       return studentService.updateStudent(id,student);
    }
    @DeleteMapping("/{id}")
    public Student deleteStudent(@PathVariable long id){
        return studentService.deleteStudent(id);
    }

    @GetMapping("/studentsAge")
    public Collection<Student> findByAgeBetween(@RequestParam int minAge,
                                                @RequestParam int maxAge){
        return studentService.findByAgeBetween(minAge,maxAge);
    }
    @GetMapping("/{id}/faculty")
    public Faculty getFacultyByStudent(@PathVariable("id") long id){
        return studentService.getFacultyByStudent(id);
    }

}
