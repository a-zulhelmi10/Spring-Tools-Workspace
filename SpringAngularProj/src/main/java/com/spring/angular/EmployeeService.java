package com.spring.angular;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {
	@Autowired
	private EmployeeRepository employeeRepository;
	public List<Employee> getAllEmployee(){
		return employeeRepository.findAll();
	}
}
