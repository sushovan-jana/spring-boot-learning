package com.kodnest.app.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kodnest.app.entities.Employee;
import com.kodnest.app.repositories.EmployeeRepo;

@Service
public class EmployeeServices {
	
	EmployeeRepo employeeRepo;

	public EmployeeServices(EmployeeRepo employeeRepo) {
		super();
		this.employeeRepo = employeeRepo;
	}
	
	public List<Employee> getAllEmployees() {
		return employeeRepo.findAll();
	}
	
	public Employee getEmployee(int id) {
		return employeeRepo.findById(id).orElse(null);
	}
	
	public void deleteEmployee(Employee employee) {
		employeeRepo.delete(employee);
	}
	
	public Employee addEmployee(Employee employee) {
		return employeeRepo.save(employee);
	}
	
}

