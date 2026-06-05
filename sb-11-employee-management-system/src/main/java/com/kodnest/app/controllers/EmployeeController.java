package com.kodnest.app.controllers;

import java.util.List;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.kodnest.app.entities.Employee;
import com.kodnest.app.services.EmployeeServices;

@RestController
@RequestMapping("/api")
public class EmployeeController {

	EmployeeServices employeeServices;
//	Dependency Injection
	public EmployeeController(EmployeeServices employeeServices) {
		this.employeeServices = employeeServices;
	}

	@GetMapping("/employees")
	public List<Employee> getAllEmployees() {
		return employeeServices.getAllEmployees();
	}

	@GetMapping("/employee/{uid}")
	public Employee getEmployee(@PathVariable("uid") int id) {
		return employeeServices.getEmployee(id);
	}

	@PostMapping("/employee")
	public Employee createEmployee(@RequestBody Employee employee) {
		return employeeServices.addEmployee(employee);
	}

	@DeleteMapping("/employee/{uid}")
	public String deleteEmployee(@PathVariable("uid") int id) {
		Employee employee = employeeServices.getEmployee(id);
		if (employee != null) {
			employeeServices.deleteEmployee(employee);
			return "Employee with id " + id + " deleted successfully.";
		} else {
			return "Employee with id " + id + " does not exist to delete.";
		}
	}
//	Here we are taking the employee object from the front-end part in json format
//	Then capturing the object from the database in java object format by using json format object's id
//	Updating the object and saving	
	@PutMapping("/employee")
	public Employee updateEmployee(@RequestBody Employee employee) {
		Employee employeeExisting = employeeServices.getEmployee(employee.getId());
		if (employeeExisting != null) {
			employeeExisting.setName(employee.getName());
			employeeExisting.setGender(employee.getGender());
			employeeExisting.setSalary(employee.getSalary());
			return employeeServices.addEmployee(employeeExisting);
		} else {
			return null;
		}
	}
}

