package com.oppositivesolutions.department.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.oppositivesolutions.department.entity.Department;
import com.oppositivesolutions.department.service.DepartmentService;

@WebMvcTest(DepartmentController.class)
class DepartmentControllerTest {

	@Autowired
	private DepartmentController departmentController;

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private DepartmentService departmentService;

	@Test
	void testCreateDepartment() throws Exception {

		// Mock the department data we need to save
		Department department = new Department();
		department.setDepartmentName("IT");
		department.setDepartmentCode("DEPT02");
		department.setDepartmentAddress("2nd floor, Block F");
		department.setDepartmentId((long) 2);

		when(departmentController.createDepartment(ArgumentMatchers.any(Department.class))).thenReturn(department);

		// Mock the request '/dept/create' to create a department
		mockMvc.perform(MockMvcRequestBuilders.post("/dept/create").contentType(MediaType.APPLICATION_JSON)
				.content(new ObjectMapper().writeValueAsString(department))).andDo(print())
				.andExpect(status().isOk()).andExpect(MockMvcResultMatchers.jsonPath("departmentId ").exists())
				.andExpect(MockMvcResultMatchers.jsonPath("departmentName").value("IT"))
				.andExpect(MockMvcResultMatchers.jsonPath("departmentCode").value("DEPT02"))
				.andExpect(MockMvcResultMatchers.jsonPath("departmentAddress").value("2nd floor, Block F"));

	}

	@Test
	void testFindDepartmentById() throws Exception {

		// Mock the data returned by DepartmentService class
		Department department = new Department();
		department.setDepartmentName("IT");
		department.setDepartmentCode("DEPT02");
		department.setDepartmentAddress("2nd floor, Block F");
		department.setDepartmentId((long) 2);

		when(departmentController.findDepartmentById(ArgumentMatchers.anyLong())).thenReturn(department);

		// create a mock HTTP request to verify the expected result.
		mockMvc.perform(MockMvcRequestBuilders.get("/dept/2")).andDo(print()).andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("departmentName").value("IT"))
				.andExpect(MockMvcResultMatchers.jsonPath("departmentCode").value("DEPT02"))
				.andExpect(MockMvcResultMatchers.jsonPath("departmentAddress").value("2nd floor, Block F"))
				.andExpect(MockMvcResultMatchers.jsonPath("departmentId").value("2"));
	}

}
