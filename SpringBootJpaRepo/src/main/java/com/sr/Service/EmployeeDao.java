package com.sr.Service;

import java.util.List;

import com.sr.Model.Employee;

public interface EmployeeDao 
{
	public List<Employee> getAllEmp();
	public Employee addEmp(Employee emp);

}
