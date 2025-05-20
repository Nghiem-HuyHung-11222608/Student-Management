package com.example.studentmanagment.service;

import com.example.studentmanagment.model.Student;
import com.example.studentmanagment.model.Course;
import com.example.studentmanagment.repository.StudentRepository;
import com.example.studentmanagment.repository.CourseRepository;
import com.example.studentmanagment.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Set;

@Service
public class StudentService {
    private final StudentRepository studentRepo;
    private final CourseRepository courseRepo;

    public StudentService(StudentRepository studentRepo, CourseRepository courseRepo) {
        this.studentRepo = studentRepo;
        this.courseRepo = courseRepo;
    }
//Add a new student
    public Student addStudent(Student student) {
        return studentRepo.save(student);
    }
    //Search all students
    public List<Student> getAllStudents() {
        return studentRepo.findAll();
    }
    //Get student by id
    public Student getStudentById(Long id) {
        return studentRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found: " + id));
    }
    //Update student
    public Student updateStudent(Long id, Student updated) {
        Student s = getStudentById(id);
        s.setName(updated.getName());
        s.setEmail(updated.getEmail());
        s.setAge(updated.getAge());
        return studentRepo.save(s);
    }
    //Delete student
    public void deleteStudent(Long id) {
        studentRepo.deleteById(id);
    }
    //Search student by name
    public List<Student> searchStudentsByName(String name) {
        return studentRepo.findByNameContainingIgnoreCase(name);
    }
    //Entroll in Courses
    @Transactional
    public Student enrollCourses(Long studentId, Set<Long> courseIds) {
        Student student = getStudentById(studentId);
        Set<Course> courses = student.getCourses();
        for(Long cid : courseIds) {
            Course course = courseRepo.findById(cid)
                    .orElseThrow(() -> new ResourceNotFoundException("Course not found: " + cid));
            courses.add(course);
            course.getStudents().add(student);
            courseRepo.save(course);
        }
        student.setCourses(courses);
        return studentRepo.save(student);
    }
    //List all courses of student in
    public Set<Course> getCoursesOfStudent(Long studentId) {
        return getStudentById(studentId).getCourses();
    }
}