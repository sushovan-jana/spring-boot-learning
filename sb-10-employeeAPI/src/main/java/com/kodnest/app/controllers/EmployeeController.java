package com.kodnest.app.controllers;

import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.kodnest.app.entities.Employee;
import com.kodnest.app.services.EmployeeService;

@RestController
@RequestMapping("/api")
public class EmployeeController {
	
	EmployeeService employeeService;
	
	public EmployeeController(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}
	
	@GetMapping("/employees")
	public List<Employee> getAllEmployees() {
		return employeeService.getAllEmployees();
	}
	
	@GetMapping("/employee/{uid}")
	public Employee getEmployeeById(@PathVariable("uid") int id) {
		return employeeService.getEmployee(id);
	}
}
