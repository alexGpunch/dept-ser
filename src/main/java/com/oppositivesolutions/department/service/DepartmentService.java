package com.oppositivesolutions.department.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oppositivesolutions.department.entity.Department;
import com.oppositivesolutions.department.repository.DepartmentRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class DepartmentService {
	
	@Autowired
	private DepartmentRepository departmentRepository;

	public Department createDepartment(Department department) {
		log.info("Inside CreateDepartment method in DepartmentService");
		log.info("department.getDepartmentAddress()" + department.getDepartmentAddress());
		log.info("department.getDepartmentCode()" + department.getDepartmentCode());
		log.info("department.getDepartmentName()" + department.getDepartmentName());
		log.info("department.getDepartmentId()" + department.getDepartmentId());
		return departmentRepository.save(department);
	}

	public Department findDepartmentById(Long deptId) {
		log.info("Inside findDepartmentById method in DepartmentService");
		return departmentRepository.findByDepartmentId(deptId);
	}

}
