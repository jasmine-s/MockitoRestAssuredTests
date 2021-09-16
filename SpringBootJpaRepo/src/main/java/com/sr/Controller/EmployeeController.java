package com.sr.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sr.Model.Employee;
import com.sr.Service.EmployeeDao;

@RestController
@RequestMapping("api/v1")
public class EmployeeController 
{
	
	@Autowired
	private EmployeeDao empDao;
	
	
	

	@GetMapping("/getAll") // endpoints
	public ResponseEntity<?> getAllEmp()
	{
		List<Employee> emplist = empDao.getAllEmp();
		if(emplist!=null)
		{
			return new ResponseEntity<List<Employee>>(emplist,HttpStatus.OK);
		}
		
		return new ResponseEntity<String>("Emp list is empty!", HttpStatus.NO_CONTENT);
		
	}
	
	@PostMapping(value="/addEmp", consumes="application/json; charset=utf-8") // endpoints
	public ResponseEntity<?> addEmployee(@RequestBody Employee emp)
	{
		
		if(empDao.addEmp(emp)!=null)
		{
			return new ResponseEntity<Employee>(emp,HttpStatus.CREATED);
		}
		
		return new ResponseEntity<String>("Sorry cannot enter data", HttpStatus.CONFLICT);
		
	}

}











