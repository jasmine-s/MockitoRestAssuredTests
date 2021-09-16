package com.sr.demo;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.any;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sr.Controller.EmployeeController;
import com.sr.Model.Employee;
import com.sr.Repository .EmployeeRepository;
import com.sr.Service.EmployeeServiceImpl;

@SpringBootTest
@AutoConfigureMockMvc

public class EmployeeControllerTests {
	@Mock
	private EmployeeServiceImpl employeeServiceImpl ;
	
	@InjectMocks
	private EmployeeController employeeController;
	
	@Autowired
	private MockMvc mockMvc;
	
	private List<Employee> emplist= new ArrayList<>();
	
	@BeforeEach
	public void init()
	{
		MockitoAnnotations.initMocks(this);
		mockMvc= MockMvcBuilders.standaloneSetup(employeeController).build();
	}
	
	@Test
	public void getAllEmployeesSuccessTest() throws Exception
	{
		Employee employee = new Employee();
		employee.setEid(550);
		employee.setName("Jasmine");
		emplist.add(employee);
		when(employeeServiceImpl.getAllEmp()).thenReturn(emplist);

			
		 mockMvc.perform( MockMvcRequestBuilders
			      .get("/getAll")
			      .accept(MediaType.APPLICATION_JSON))
			      .andDo(print())
			      .andExpect(status().isOk())
			      .andExpect(MockMvcResultMatchers.jsonPath("$.employees").hasJsonPath())
			      .andExpect(MockMvcResultMatchers.jsonPath("$.employees[*].employeeId").hasJsonPath());
		 assertEquals(1 , emplist.size());
			
	}

	
	@Test
	public void getAllEmployeesFailureTest() throws Exception
	{
		emplist.clear();
		
		when(employeeServiceImpl.getAllEmp()).thenReturn(null);
		
		
	mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/employee/all").contentType(MediaType.APPLICATION_JSON))
							.andExpect(MockMvcResultMatchers.status().isNotFound());

	assertNotEquals(1, emplist.size());
		
	}
	
	@Test
	public void createEmployeeTest() throws Exception
	{
		
		emplist.clear();
		Employee employee = new Employee();
		employee.setEid(50);
		employee.setName("Jasmine");
		emplist.add(employee);
		//System.out.print(employee);
		when(employeeServiceImpl.addEmp(employee)).thenReturn(null);
		
		assertEquals(1,emplist.size());
		
		
	mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/employee/addEmp")
							.contentType(MediaType.APPLICATION_JSON)
							.content(new ObjectMapper().writeValueAsString(employee)))
							.andExpect(MockMvcResultMatchers.status().isCreated());
	
		
	}
	
	
	@Test
	public void createEmployeeFailureTest() throws Exception
	{
		emplist.clear();
		Employee employee = new Employee();
		employee.setEid(50);
		employee.setName("Jasmine");
		emplist.add(employee);
		System.out.print(employee);
		when(employeeServiceImpl.addEmp(employee)).thenReturn(null);
		
		assertNotEquals(0,emplist.size());
		
		mockMvc.perform(MockMvcRequestBuilders
				.post("/api/v1/employee/addEmployee")
				.contentType(MediaType.APPLICATION_JSON)
				.content(new ObjectMapper().writeValueAsString(employee)))
		     .andExpect(MockMvcResultMatchers.status().isNotFound());
	
		
	}
}
