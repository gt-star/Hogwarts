package ru.hogwarts.school.service;

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
//    @Autowired
    private final StudentRepository studentRepository;
    private final AvatarRepository avatarRepository;

    public StudentService(StudentRepository studentRepository, AvatarRepository avatarRepository) {
        this.studentRepository = studentRepository;
        this.avatarRepository = avatarRepository;
    }
    public Student createStudent(Student student){
        student.setId(null);
        return studentRepository.save(student);
    }
    public Student getStudent(long id){
        return studentRepository.findById(id).orElseThrow(()->new StudentNotFoundException(id));
    }
    public Student updateStudent(long id, Student newStudent){
        Student oldStudent = getStudent(id);
        oldStudent.setAge(newStudent.getAge());
        oldStudent.setName(newStudent.getName());
        return studentRepository.save(oldStudent);
    }
    public Student deleteStudent(long id){
        Student student = getStudent(id);
        studentRepository.delete(student);
        return student;
    }

    public Collection<Student> findByAgeBetween(int min, int max){
        return studentRepository.findByAgeBetween(min,max);
    }

    public Faculty getFacultyByStudent(long id) {
        return studentRepository.findById(id)
                .map(Student::getFaculty)
                .orElseThrow(() -> new FacultyNotFoundException(id));
    }

}
