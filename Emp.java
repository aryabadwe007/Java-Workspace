package com.demo.test;

import java.io.File;
import java.util.List;
import java.util.Scanner;

import com.demo.beans.Employee;
import com.demo.service.EmployeeService;
import com.demo.service.EmployeeServiceImpl;

public class TestEmployeeManagementSystem {

	private static File file;

	public static void main(String[] args) {
		
		
		
		Scanner sc = new Scanner (System.in);
		
		EmployeeService eservice = new EmployeeServiceImpl();
		
		int choice = 0;
		do {
			System.out.println("1. Add new Employee\n 2. Display All\n 3. Search by id\n");
			System.out.println("4. Search by Name\n 5. Search By Salary\n 6. Update Salary\n");
			System.out.println("7. Delete By id\n 8. Delete by Salary\n 9. Sort by Salary\n 10. Exit\n choice : ");
			choice = sc.nextInt();
			
			
			
			switch(choice) {
			case 1 -> {
				boolean status = eservice.addNewEmployee();
				if (status) {
					System.out.println("Added Successfully");
				}else {
					System.out.println("Not Added");
				}
			}
			
			case 2 ->{
				List <Employee> elist = eservice.displayAll();
				elist.forEach(System.out::println);
			}
			
			case 3 -> {
				System.out.println("Enter id : ");
				int empid = sc.nextInt();
				Employee e = eservice.searchById(empid);
				if(e!=null) {
					System.out.println(e);
				}else {
					System.out.println("Not Found");
				}
			}
			
			case 4 ->{
				System.out.println("Enter Name : ");
				String ename = sc.next();
				List<Employee> e = eservice.searchByName(ename);
				
			}
			
			case 5 ->{
				
			}
			
			case 7 ->{
				System.out.println("enetr id for delete");
				int empid=sc.nextInt();
				boolean status=eservice.deleteById(empid);
				if(status) {
					System.out.println("deleted successfully");
				}else {
					System.out.println("not found");
				}
			}
			
			case 8 ->{
				System.out.println("enetr salary for delete");
				double sal=sc.nextDouble();
				boolean status=eservice.deleteBySalary(sal);
				if(status) {
					System.out.println("deleted successfully");
				}else {
					System.out.println("not found");
				}
			}
			
			case 9 ->{
				 List<Employee> sortedList = eservice.sortBySalary();
				    System.out.println("Employees sorted by salary (ascending):");
				    sortedList.forEach(System.out::println);
			}
			
			case 10 ->{
				eservice.writeFile(file);
				System.out.println("Existing...data saved");
			}
			}
			
		}while(choice!=10);

	}

}






// serviceimpl


package com.demo.service;

import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

import com.demo.beans.Employee;
import com.demo.dao.EmployeeDao;
import com.demo.dao.EmployeeDaoImpl;

public class EmployeeServiceImpl implements EmployeeService {
	
	private EmployeeDao edao;
	
	
	public EmployeeServiceImpl() {
		super();
		edao = new EmployeeDaoImpl();
	}


	@Override
	public boolean addNewEmployee() {
		Scanner sc = new Scanner (System.in);
		
		System.out.println("Enter id : ");
		int empid = sc.nextInt();
		
		System.out.println("Enter Name : ");
		String name = sc.next();
		
		System.out.println("Enter Salary : ");
		double sal = sc.nextDouble();
		
		System.out.println("Enter Joining Date (yyyy/MM/dd) : ");
		String dt = sc.next();
		LocalDate ldt = LocalDate.parse(dt, DateTimeFormatter.ofPattern("yyyy/MM/dd"));
		
		Employee e = new Employee (empid, name, sal, ldt);
		return edao.save(e);
	}


	@Override
	public List<Employee> displayAll() {
		
		return edao.findAll();
	}


	@Override
	public Employee searchById(int empid) {
		
		return edao.findById(empid);
	}


	@Override
	public List<Employee> searchByName(String ename) {
		
		return edao.findByName(ename);
	}


	@Override
	public boolean deleteById(int empid) {
		// TODO Auto-generated method stub
		return edao.deleteById(empid);
	}


	@Override
	public boolean deleteBySalary(double sal) {
		
		return edao.deleteBySalary(sal);
	}


	@Override
	public List<Employee> sortBySalary() {
		// TODO Auto-generated method stub
		return edao.sortBySalary();
	}


	@Override
	public void readFile(File fs) {
		
		 edao.readFile(fs);
	}


	@Override
	public void writeFile(File fs) {
		edao.writeFile(fs);
		
	}
}




// Dao

package com.demo.dao;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.demo.beans.Employee;

public class EmployeeDaoImpl implements EmployeeDao {

    static List<Employee> elist;

    static {
        elist = new ArrayList<>();
        elist.add(new Employee(100, "Ayan", 45000, LocalDate.of(2003, 7, 1)));
        elist.add(new Employee(101, "Aryan", 55000, LocalDate.of(2005, 2, 3)));
        elist.add(new Employee(104, "Pratik", 35000, LocalDate.of(2004, 3, 5)));
        elist.add(new Employee(102, "Krishna", 25000, LocalDate.of(2007, 2, 2)));
    }

    @Override
    public boolean save(Employee e) {
        return elist.add(e);
    }

    @Override
    public List<Employee> findAll() {
        return elist;
    }

    @Override
    public Employee findById(int empid) {
        int pos = elist.indexOf(new Employee(empid));
        if (pos != -1) {
            return elist.get(pos);
        }
        return null;
    }

    @Override
    public List<Employee> findByName(String ename) {
        List<Employee> temp = elist.stream()
                .filter(emp -> emp.getEname().equalsIgnoreCase(ename))
                .collect(Collectors.toList());
        return temp.isEmpty() ? null : temp;
    }

    @Override
    public boolean deleteById(int empid) {
        return elist.remove(new Employee(empid));
    }

    @Override
    public boolean deleteBySalary(double sal) {
        return elist.removeIf(emp -> emp.getSal() < sal);
    }

    @Override
    public List<Employee> sortBySalary() {
        return elist.stream()
                .sorted((e1, e2) -> Double.compare(e1.getSalary(), e2.getSalary()))
                .collect(Collectors.toList());
    }

 
    @Override
    public void readFile(File fs) {
        if (fs.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fs))) {
                elist.clear(); // clear old default data
                while (true) {
                    Employee e = (Employee) ois.readObject();
                    elist.add(e);
                }
            } catch (EOFException e) {
                System.out.println("Employee data loaded successfully from file.");
            } catch (Exception e) {
                System.out.println("Error reading file: " + e.getMessage());
            }
        } else {
            System.out.println("File not found. Using default employees.");
        }
    }

    @Override
    public void writeFile(File fs) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fs))) {
            for (Employee employee : elist) {
                oos.writeObject(employee);
            }
            System.out.println("Employee data saved successfully to file.");
        } catch (Exception e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }
}
