package com.demo.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

import com.demo.beans.Employee;
import com.demo.dao.EmployeeDao;
import com.demo.dao.EmployeeDaoImpl;

public class EmployeeServiceImpl implements EmployeeService {
	// "implements" is used because it is a interface
	// "extends" is used if it is a class
	
	private EmployeeDao edao;
	
	public EmployeeServiceImpl() {
		edao = new EmployeeDaoImpl();
	}
	
	
		@Override
		public boolean addNewEmployee() {
			Scanner sc=new Scanner(System.in);
			System.out.println("enter id");
			int eid=sc.nextInt();
			System.out.println("Enter name");
			String name=sc.next();
			System.out.println("enetr salary");
			double salary=sc.nextDouble();
			System.out.println("entr joining date (dd/mm/yyyy)");
			String dt=sc.next();
			LocalDate ldt=LocalDate.parse(dt,DateTimeFormatter.ofPattern("dd/MM/yyyy"));
			Employee e=new Employee(eid,name,salary,ldt);
			return edao.save(e);
	}

		@Override
		public List<Employee> displayAll() {
			return edao.findAll();
		}


		@Override
		public Employee searchById(int eid) {
			return edao.findById(eid);
		}


		@Override
		public boolean deleteById(int eid) {
			return edao.removeById(eid);
		}


		@Override
		public List<Employee> findByName(String nm) {
			return edao.findByName(nm);
		}





		public List<Employee> findBySalary(double salary) {
			// TODO Auto-generated method stub
			return null;
		}


		


		public boolean updateById(int eid, double sal) {
			return edao.updateById(eid,sal);
			
			
		}


		public boolean deleteBySal(double sal) {
			return edao.deleteBySal(sal); 
			
		}




		public List<Employee> sortBySal(double sal) {
			// TODO Auto-generated method stub
			return edao.sortBySal(sal);
		}




	}
