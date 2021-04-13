package com.oppositivesolutions.department.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.oppositivesolutions.department.entity.Department;
import com.oppositivesolutions.department.service.DepartmentService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/dept")
@Slf4j
public class DepartmentController {
	
	@Autowired
	private DepartmentService departmentService;
	
	@PostMapping("/create")
	public Department createDepartment(@RequestBody Department department) {
		log.info("Inside CreateDepartment method in DepartmentController");
		return departmentService.createDepartment(department);
		
	}
	
	@GetMapping("/{id}")
	public Department findDepartmentById(@PathVariable("id") Long deptId) {
		log.info("Inside findDepartmentById method in DepartmentController");
		return departmentService.findDepartmentById(deptId);
	}

}
