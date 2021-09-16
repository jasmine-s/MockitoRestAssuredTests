package com.sr.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.sr.Model.Employee;
import com.sr.Repository.EmployeeRepository;
import com.sr.Service.EmployeeServiceImpl;



public class EmployeeServiceTest {
	
	@Mock
	private EmployeeRepository employeeRepository;
	
	@InjectMocks
	private EmployeeServiceImpl employeeService;
	
	@Autowired
	private MockMvc mockMvc;
	
	private List<Employee> emplist = new ArrayList<>();
	
	@BeforeEach
	public void init()
	{
		MockitoAnnotations.initMocks(this);
		mockMvc= MockMvcBuilders.standaloneSetup(employeeService).build();
	}
	
	@Test
	public void getAllEmpSuccess() throws Exception
	{
		Employee emp = new Employee();
		emp.setEid(201);
		emp.setName("Chris");
		emplist.add(emp);
		
		when(employeeRepository.findAll()).thenReturn(emplist);
		List<Employee> elist = employeeService.getAllEmp();
		assertEquals(emplist, elist);
		
	}

	
	@Test
	public void getAllEmpFailure() throws Exception
	{
				
		when(employeeRepository.findAll()).thenReturn(null);
		List<Employee> elist = employeeService.getAllEmp();
		assertNull( elist);
		
	}
	
	
	
	
	@Test
	public void addAllEmpSuccess() throws Exception
	{
		Employee emp = new Employee();
		emp.setEid(201);
		emp.setName("Chris");
		emplist.add(emp);
		
		when(employeeRepository.saveAndFlush(any())).thenReturn(emp);
		Employee e1 = employeeService.addEmp(emp);
		assertEquals(emp, e1);
		
	}

	
	@Test
	public void addAllUserFailure() throws Exception
	{
				
		Employee emp = new Employee();
		emp.setEid(201);
		emp.setName("Chris");
		emplist.add(emp);
		
		
		when(employeeRepository.save(any())).thenReturn(null);
		Employee e1 = employeeService.addEmp(emp);
		assertNull(e1);
		
	}

}




