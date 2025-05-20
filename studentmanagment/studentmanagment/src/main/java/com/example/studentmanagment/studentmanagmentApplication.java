package com.example.studentmanagment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class studentmanagmentApplication {
	public static void main(String[] args) {
		SpringApplication.run(studentmanagmentApplication.class, args);
	}
}

/*  Test
Add Student:http://localhost:8080/api/students
	{"name":"Nghiem Huy Hung","age":21,"email":"Vodanhfx29102004@mail.com" }
	{"name":"Nguyen Van A","age":19,"email":"Skibiditoilet@gmail.com" }

Search Student by name: http://localhost:8080/api/students/search?name="stundentName"

Add Course:	http://localhost:8080/api/courses
{"name":"Math"}
{"name":"English"}

Enroll in Courses: http://localhost:8080/api/students/studentId/enroll
	{ "courseIds": [1,2] }

Search Course by id: http://localhost:8080/api/courses/"id"
Students in a Course: http://localhost:8080/api/students/1/courses
 */
