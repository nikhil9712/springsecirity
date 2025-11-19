package com.Security.usermanagement.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Security.usermanagement.dto.Employee;

@RestController
public class EmployeeController {


	@GetMapping("/getEmployees")
	public List<Employee> getEmployees() {
		List<Employee> employees = new ArrayList<>();
		Employee e1 = new Employee(101, "Nikhil", 45000, "IT");
		Employee e2 = new Employee(102, "Aditi", 50000, "Finance");
		Employee e3 = new Employee(103, "Sagar", 47000, "HR");
		Employee e4 = new Employee(104, "krushna", 52000, "IT");
		Employee e5 = new Employee(105, "Nikita", 46000, "Sales");
		employees.add(e1);
		employees.add(e2);
		employees.add(e3);
		employees.add(e4);
		employees.add(e5);
		System.out.println(employees);
		return employees;
	}
}
