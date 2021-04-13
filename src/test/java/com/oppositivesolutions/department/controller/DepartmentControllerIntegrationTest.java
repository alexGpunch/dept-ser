package com.oppositivesolutions.department.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.test.context.jdbc.Sql;

import com.oppositivesolutions.department.entity.Department;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class DepartmentControllerIntegrationTest {

	@Autowired
	private TestRestTemplate testRestTemplate;

	@Test
	@Sql(scripts= {"classpath:testGetDepartmentById.sql"})
	public void testGetDepartmentById() {
		
		Department department = testRestTemplate.getForObject("/dept/19", Department.class);
		
		assertEquals(19, department.getDepartmentId());
		assertEquals("Auckland", department.getDepartmentAddress());
		assertEquals("DEPT19", department.getDepartmentCode());
		assertEquals("MS", department.getDepartmentName());
	}
	
	
	@Test
	public void testCreateDepartment() {
		
		Department department = new Department();
		department.setDepartmentName("ITFS");
		department.setDepartmentCode("DEPT21");
		department.setDepartmentAddress("2nd floor, Block F");
//		department.setDepartmentId((long) 2);
		
		HttpEntity<Department> request = new HttpEntity<>(department);
		
		Department departmentReturned = testRestTemplate.postForObject("/dept/create", request, Department.class);
		
		assertNotNull(departmentReturned.getDepartmentId());
		assertEquals("2nd floor, Block F", department.getDepartmentAddress());
		assertEquals("DEPT21", department.getDepartmentCode());
		assertEquals("ITFS", department.getDepartmentName());
	}

}
