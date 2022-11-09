SELECT students.name, students.age, faculties.name FROM students
LEFT JOIN faculties ON students.faculty_id = faculties.id;

SELECT student.name FROM student
INNER JOIN avatar ON student.id = avatar.student_id;