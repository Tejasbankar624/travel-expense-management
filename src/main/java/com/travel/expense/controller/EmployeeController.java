package com.travel.expense.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/employee")
public class EmployeeController {
	
	@GetMapping("/dashboard")
	public String employeeAccess() {
		return "Employee Dashboard Accessed";
	}

}
