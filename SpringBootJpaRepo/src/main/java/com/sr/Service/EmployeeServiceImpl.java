package com.sr.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sr.Model.Employee;
import com.sr.Repository.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements EmployeeDao
{

	@Autowired
	private EmployeeRepository empRepo;
	
	
	@Override
	public List<Employee> getAllEmp() {
		List<Employee> emplist = empRepo.findAll();
		
		if(emplist != null && emplist.size()>0)
		{
			return emplist;
		}
		return null;
		
	}

	@Override
	public Employee addEmp(Employee emp) 
	{
		if(emp!=null)
		{
			return empRepo.saveAndFlush(emp);
		}
		else
			return null;
		
	}

}












