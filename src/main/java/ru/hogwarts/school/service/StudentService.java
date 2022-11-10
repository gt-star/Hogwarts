package ru.hogwarts.school.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.exception.FacultyNotFoundException;
import ru.hogwarts.school.exception.StudentNotFoundException;
import ru.hogwarts.school.model.Avatar;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.AvatarRepository;
import ru.hogwarts.school.repository.StudentRepository;

import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;

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

    public Stream<String> findStudentNameStartedWithA() {
        return studentRepository.findAll().stream()
                .map(Student::getName)
                .map(String::toUpperCase)
                .filter(s -> s.startsWith("A"))
                .sorted();
    }

    public double findStudentAverageAge() {
        return studentRepository.findAll().stream()
                .mapToDouble(Student::getAge)
                .average()
                .orElseThrow();
    }
    public int streamParallel() {
        Stream<Integer> computed = Stream
                .iterate(1, a -> a + 1)
                .parallel()
                .limit(1_000_000);
        int sum = computed.reduce(0, (a, b) -> a + b );
        return sum;
    }

    public void printStudents() {
        List<Student> students = studentRepository.findAll(PageRequest.of(0,6)).getContent();

        printStudents(students.subList(0,2));
        new Thread(()-> printStudents(students.subList(2,4))).start();
        new Thread(()-> printStudents(students.subList(4,4))).start();
    }
    private void printStudents(List<Student> students){
        for (Student student : students){
            logger.info(student.getName());
        }
    }
    public void printStudentsSync() {
        List<Student> students = studentRepository.findAll(PageRequest.of(0,6)).getContent();

        printStudentsSync(students.subList(0,2));
        new Thread(()-> printStudents(students.subList(2,4))).start();
        new Thread(()-> printStudents(students.subList(4,4))).start();
    }
    private synchronized void printStudentsSync(List<Student> students){
        for (Student student : students){
            logger.info(student.getName());
        }
    }
}
