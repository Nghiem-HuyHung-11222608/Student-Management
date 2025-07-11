package com.lms.studentmanagement.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;


@Entity
@Getter
@Setter
@NoArgsConstructor
public class LessonResult {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonIgnore
    @ManyToOne
    private Lesson lesson;


    @ManyToOne
    private User user;

    @Enumerated(EnumType.STRING)
    private Status status;
    private String uploadedFile;
    private String comment;
    private Date updatedAt;

    public enum Status {
        IN_PROGRESS, DONE
    }
}