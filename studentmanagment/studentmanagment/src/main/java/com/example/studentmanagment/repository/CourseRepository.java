    package com.example.studentmanagment.repository;

    import com.example.studentmanagment.model.Course;
    import org.springframework.data.jpa.repository.JpaRepository;

    public interface CourseRepository extends JpaRepository<Course, Long> {
    }