package ru.hogwarts.school.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.service.StudentService;

import java.util.Collection;
import java.util.stream.Stream;

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
    @GetMapping("/countStudents")
    public Integer getAllByCountStudent(){
        return studentService.getAllByCountStudent();
    }
    @GetMapping("/averageAge")
    public Double getAverageAge(){
        return studentService.getAverageAge();
    }
    @GetMapping("/lastFiveStudents")
    public Collection<Student> getLastOfFiveStudents(){
        return studentService.getLastOfFiveStudents();
    }
    @GetMapping("/findStudentNameStartedWithA")
    public Stream<String> findStudentNameStartedWithA(){
        return studentService.findStudentNameStartedWithA();
    }
    @GetMapping("/findStudentAverageAge")
    public double findStudentAverageAge(){
        return studentService.findStudentAverageAge();
    }
    @GetMapping("/streamParallel")
    public int streamParallel() {
        return studentService.streamParallel();
    }
    @GetMapping("/printStudents")
    public void printStudents(){
        studentService.printStudents();
    }
    @GetMapping("/printStudentsSync")
    public void printStudentsSync(){
        studentService.printStudentsSync();
    }

}
