package com.example.studentmanagment.service.impl;

//import com.example.studentmanagment.repository.StudentRepository;
//import com.example.studentmanagment.service.StudentService;


//@Service
//public class StudentServiceImpl implements StudentService {
    /*private final StudentRepository studentRepo;
    private final CourseRepository courseRepo;

    public StudentServiceImpl(StudentRepository studentRepo,CourseRepository courseRepo){
        this.studentRepo=studentRepo;
        this.courseRepo=courseRepo;
    }

    @Override
    public Student addStudent(Student student) {
        return studentRepo.save(student);
    }

    @Override
    public List<Student> getAllStudents() {
        return studentRepo.findAll();
    }

    @Override
    public Student getStudentById(Long Id){
        return studentRepo.findById(Id)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found: " + Id));
    }

    @Override
    public Student updateStudent(Long id,Student updated){
        Student s = getStudentById(id);
        s.setName(updated.getName());
        s.setEmail(updated.getEmail());
        s.setAge(updated.getAge());
        return studentRepo.save(s);
    }

    @Override
    public void deleteStudent(Long id){
        studentRepo.deleteById(id);
    }

    @Override
    public List<Student> searchStudentsByName(String name){
        return studentRepo.findByNameContainingIgnoreCase(name);
    }

    @Override
    @Transactional
    public Student enrollCourses(Long studentId,Set<Long> courseIds){
        Student student =getStudentById(studentId);
        Set<Course> courses = student.getCourses();
        for(Long cid : courseIds){
            Course course = courseRepo.findById(cid)
                    .orElseThrow(() -> new ResourceNotFoundException("Course not found: " + cid));
            courses.add(course);
            courseRepo.save(course);
        }
        student.setCourses(courses);
        return studentRepo.save(student);
    }

    @Override
    public Set<Course> getCoursesOfStudent(Long studentId){
        return getStudentById(studentId).getCourses();
    }
}*/
