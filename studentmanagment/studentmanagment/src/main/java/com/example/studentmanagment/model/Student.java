package com.example.studentmanagment.model;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Deprecated
// @Entity
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private int age;
    @Column(unique = true)
    private String email;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "student_course",
            joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "course_id")
    )
    private Set<Course> courses = new HashSet<>();

    public Student() {}
    public Student(String name, int age, String email) {
        this.name = name;
        this.age = age;
        this.email = email;
    }

    // Getters and setters
    public Long getId() { return id; }

    //Student Name
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    //Student Age
    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }


    //Student Email
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    //Student Course
    public Set<Course> getCourses() { return courses; }
    public void setCourses(Set<Course> courses) { this.courses = courses; }
}