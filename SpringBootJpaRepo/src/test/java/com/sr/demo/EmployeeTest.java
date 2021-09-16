package com.sr.demo;


import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import com.sr.Model.Employee;



public class EmployeeTest {
	@Mock
	private Employee emp = new Employee();
	@Test
	public void testid()
	{
		emp.setEid(1);
		assertEquals(1, emp.getEid());
	}
	@Test
	public void testname()
	{
		emp.setName("sravya");
		assertEquals( "sravya" ,emp.getName());
	}
	

}
