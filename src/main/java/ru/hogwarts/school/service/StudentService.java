package ru.hogwarts.school.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.exception.FacultyNotFoundException;
import ru.hogwarts.school.exception.StudentNotFoundException;
import ru.hogwarts.school.model.Avatar;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.AvatarRepository;
import ru.hogwarts.school.repository.StudentRepository;

import java.util.Collection;

@Service
public class StudentService {
    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }
    Logger logger = LoggerFactory.getLogger(StudentService.class);
    public Student createStudent(Student student){
        logger.info("Method called createStudent");
        student.setId(null);
        return studentRepository.save(student);

    }
    public Student getStudent(long id){
        logger.info("Method called getStudent");
        return studentRepository.findById(id).orElseThrow(()-> {
            logger.error("There is not student with id = " + id);
            return new StudentNotFoundException(id);
        });
    }
    public Student updateStudent(long id, Student newStudent){
        logger.info("Method called updateStudent");
        Student oldStudent = getStudent(id);
        oldStudent.setAge(newStudent.getAge());
        oldStudent.setName(newStudent.getName());
        return studentRepository.save(oldStudent);
    }
    public Student deleteStudent(long id){
        logger.info("Method called deleteStudent");
        Student student = getStudent(id);
        studentRepository.delete(student);
        return student;
    }

    public Collection<Student> findByAgeBetween(int min, int max){
        logger.info("Method called findByAgeBetween");
        return studentRepository.findByAgeBetween(min,max);
    }

    public Faculty getFacultyByStudent(long id) {
        logger.info("Method called getFacultyByStudent");
        return studentRepository.findById(id)
                .map(Student::getFaculty)
                .orElseThrow(() -> {
                    logger.error("There is not student with id = " + id);
                    return new FacultyNotFoundException(id);
                });
    }

    public Integer getAllByCountStudent(){
        logger.info("Method called getAllByCountStudent");
        return studentRepository.getAllByCountStudent();
    }

    public Double getAverageAge() {
        logger.info("Method called getAverageAge");
        return studentRepository.getAverageAge();
    }

    public Collection<Student> getLastOfFiveStudents() {
        logger.info("Method called getLastOfFiveStudents");
        return studentRepository.getLastOfFiveStudents();
    }
}
