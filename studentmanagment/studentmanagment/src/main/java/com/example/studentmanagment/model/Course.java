package com.example.studentmanagment.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class    Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String name;

    @ManyToMany(mappedBy = "courses", fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<Student> students = new HashSet<>();

    public Course() {}
    public Course(String name) { this.name = name; }

    // Getters and setters
    public Long getId() { return id; }

    //Course Name
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    //Student enroll in Course
    public Set<Student> getStudents() { return students; }
    public void setStudents(Set<Student> students) { this.students = students; }
}