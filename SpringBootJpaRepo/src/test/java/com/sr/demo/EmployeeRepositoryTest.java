package com.sr.demo;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;

import com.sr.Model.Employee;
import com.sr.Repository.EmployeeRepository;


@DataJpaTest
@AutoConfigureMockMvc
public class EmployeeRepositoryTest {
	//@Autowired
	//private EmployeeRepository emprepo;
	@Mock
	private EmployeeRepository emprepo;
	
	@InjectMocks
	private Employee employee = new Employee();
	
	
	@BeforeEach
	public void init()
	{
		Employee employee = new Employee();
		employee.setEid(01);
		employee.setName("JasmineShaik");
		
	}
	

	@Test
	public void EmployeeSaveSuccess() throws Exception
	{
		Employee e1 = null;
		emprepo.save(employee);
		
		e1 = emprepo.findById(employee.getEid()).get();
		assertEquals(employee.getName(), e1.getName());
	}
	
	
	@Test
	public void SaveFailure() throws Exception
	{
		Employee u1 = null;
		
		if(emprepo.findAll().toString().isEmpty())
		{
			emprepo.save(employee);
			u1= emprepo.findById(employee.getEid()).get();
		}
		
		assertNull(u1);
		//assertNotEquals(user.getUsername(), u1.getUsername());
	}

}