package com.kodnest.app.services;

import java.util.List;
import org.springframework.stereotype.Service;
import com.kodnest.app.entities.Employee;
import com.kodnest.app.repositories.EmployeeRepo;

@Service
public class EmployeeService {
	
	EmployeeRepo employeeRepo;
	
	public EmployeeService(EmployeeRepo employeeRepo) {
		this.employeeRepo = employeeRepo;
	}
	
	public List<Employee> getAllEmployees() {
		return employeeRepo.findAll();
	}
	
	public Employee getEmployee(int id) {
//		Optional<Employee> optionalEmployee = employeeRepo.findById(id);
//		if (optionalEmployee != null) {
//			return optionalEmployee.get();
//		} else return null;
		
		return employeeRepo.findById(id).orElse(null);
	}
}


