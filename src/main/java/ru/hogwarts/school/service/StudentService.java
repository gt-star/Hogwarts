package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.exception.StudentNotFoundException;
import ru.hogwarts.school.model.Student;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class StudentService {
    private final Map<Long, Student> studentMap;
    private long studentId = 1;

    public StudentService() {
        studentMap = new HashMap<>();
    }
    public Student createStudent(Student student){
        studentMap.put(studentId,student);
        studentId++;
        return student;
    }
    public Student getStudent(long id){
        if (!studentMap.containsKey(id)){
            throw new StudentNotFoundException(id);
        }
        return studentMap.get(id);
    }
    public Student updateStudent(long id, Student newStudent){
        Student oldStudent = getStudent(id);
        oldStudent.setAge(newStudent.getAge());
        oldStudent.setName(newStudent.getName());
        studentMap.put(id,oldStudent);
        return newStudent;
    }
    public Student deleteStudent(long id){
        if (!studentMap.containsKey(id)){
            throw new StudentNotFoundException(id);
        }
        return studentMap.remove(id);
    }

    public Collection<Student> findByAge(int age){
        return studentMap.values().stream()
                .filter(student -> student.getAge()==age)
                .collect(Collectors.toList());
    }
}
