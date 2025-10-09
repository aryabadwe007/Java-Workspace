package com.demo.dao;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

//import java.util.stream.Collectors;
import com.demo.beans.Employee;

public class EmployeeDaoImpl implements EmployeeDao{
	static List<Employee> elist = new ArrayList<>();
	static {
		
			
		elist.add(new Employee(100,"Manjiri",45678,LocalDate.of(2024, 11, 12)));
    	elist.add(new Employee(101,"Gauri",55678,LocalDate.of(2023, 11, 12)));
    	elist.add(new Employee(103,"Kanchan",35678,LocalDate.of(2020, 11, 12)));
	
}
	@Override
	public boolean save (Employee e) {
		elist.add(e);
		return true;
	}
	
	@Override
	public List<Employee> findAll() {
		return elist;
	}
	
	@Override
	public Employee findById(int eid) {
		//indexOf calls equals method internally 
		//equals method is written in employee class
		int pos=elist.indexOf(new Employee(eid));
		if(pos!=-1) {
			return elist.get(pos);
		}
		return null;
	}
	
	@Override
	public boolean removeById(int eid) {
		//remove calls equals method internally 
				//equals method is written in employee class
		return elist.remove(new Employee(eid));
	}
	
	
	@Override
	public List<Employee> findByName(String nm) {
		/*List<Employee> temp=new ArrayList<>();
		for(Employee e:elist) {
			if(e.getEname().equals(nm)) {
				temp.add(e);
			}
		}*/
		List<Employee> temp= elist.stream()
		                    .filter(emp->emp.getEname().equals(nm))
		                    .collect(Collectors.toList());
		
		if(temp.size()>0) {
			return temp;
		}
		return null;
		}
	

	@Override
	public boolean addNewEmployee() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Employee> displayAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Employee searchById(int eid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean deleteById(int eid) {
		// TODO Auto-generated method stub
		return false;
	}
	
	
	
	
	//logic to find the salary : 
	
	// 1 - Find Employees by Salary Greater Than a Given Amount
	// 2 - Find Employees by Salary Within a Range
	
	


	
	
	@Override
	public List<Employee> findBySalary(double salary) {
	    
	    // 1. Convert the list into a Stream
	    return elist.stream()
	        
	        // 2. Intermediate Operation: Filter the stream
	        //    This checks the salary of each employee (emp) against the targetSalary.
	        .filter(emp -> emp.getSal() == salary)
	        
	        // 3. Terminal Operation: Collect the results
	        //    Gathers all employees that passed the filter into a new List<Employee>.
	        .collect(Collectors.toList());
	}
	
	@Override
	public boolean updateById(int eid, double sal) {
		int pos = elist.indexOf(new Employee(eid));
		if(pos != -1) {
			Employee e = elist.get(pos);
			e .setSal(sal);
			return true;
		}
		
		return false;
		
	}
	
	
	@Override
	public boolean deleteBySal(double sal) {
		return elist.removeIf(emp->emp.getSal()>sal);
	}

	@Override
	public List<Employee> sortBySal(double sal) {
	
		
			List<Employee> lst=new ArrayList<>();
			for(Employee e:elist) {
				lst.add(e);
			}
			
			
			Comparator<Employee> c = (o1, o2) -> {
			    System.out.println("In functional comparator");
			    // Corrected line: Use Integer.compare() for safe comparison of primitive ints.
			    return Double.compare(o1.getSal(), o2.getSal()); 
			};
			lst.sort(c);
			return lst;
		}
		
}
	

