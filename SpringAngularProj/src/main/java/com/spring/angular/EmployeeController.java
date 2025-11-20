package com.spring.angular;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
     
@RestController
@CrossOrigin(origins="http://localhost:4200")
@RequestMapping("/api/employees")
public class EmployeeController {
	@Autowired
	private EmployeeService employeeService;
	@GetMapping("/allemployees")
	public List<Employee> getAllEmployeesList(){
		return employeeService.getAllEmployee();    
	}
}
/*
 * Rest API to access employee service http://localhost:8080/api/employees/allemployees
 */
