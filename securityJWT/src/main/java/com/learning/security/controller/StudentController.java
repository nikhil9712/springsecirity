package com.learning.security.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.learning.security.entity.User;
import com.learning.security.model.Student;
import com.learning.security.repository.UserRepo;

import jakarta.servlet.http.HttpServletRequest;

@RestController
public class StudentController {

	private List<Student> students = new ArrayList<>(
			List.of(new Student(1, "Nikhil", "Yavatmal"), new Student(2, "Sanket", "Amravati")));

	@GetMapping("csrf-token")
	public CsrfToken getMessage(HttpServletRequest request) {
		return (CsrfToken) request.getAttribute("_csrf");
	}

	@GetMapping("getStudents")
	public List<Student> getStudent() {
		return this.students;
	}

	@PostMapping("saveStudent")
	public Student addStudent(@RequestBody Student student) {
		students.add(student);
		System.out.println("The Latest updated Student list is :" + students);
		return student;
	}

}
