package com.demo.test;

import java.io.File;
import java.util.List;
import java.util.Scanner;
import com.demo.beans.Employee;
import com.demo.service.EmployeeService;
import com.demo.service.EmployeeServiceImpl;

public class TestEmployeeManagementSystem {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        EmployeeService eservice = new EmployeeServiceImpl();

        File file = new File("employees.dat");

        // read data from file at start
        eservice.readFile(file);

        int choice = 0;
        do {
            System.out.println("""
                    1. Add new Employee
                    2. Display All
                    3. Search by id
                    4. Search by Name
                    5. Search By Salary
                    6. Update Salary
                    7. Delete By id
                    8. Delete by Salary
                    9. Sort by Salary
                    10. Exit
                    Enter choice :
                    """);
            choice = sc.nextInt();

            switch (choice) {
                case 1 -> {
                    boolean status = eservice.addNewEmployee();
                    if (status)
                        System.out.println("Added Successfully");
                    else
                        System.out.println("Not Added");
                }
                case 2 -> {
                    List<Employee> elist = eservice.displayAll();
                    elist.forEach(System.out::println);
                }
                case 3 -> {
                    System.out.println("Enter id : ");
                    int empid = sc.nextInt();
                    Employee e = eservice.searchById(empid);
                    if (e != null)
                        System.out.println(e);
                    else
                        System.out.println("Not Found");
                }
                case 4 -> {
                    System.out.println("Enter Name : ");
                    String ename = sc.next();
                    List<Employee> e = eservice.searchByName(ename);
                    e.forEach(System.out::println);
                }
                case 7 -> {
                    System.out.println("Enter id to delete: ");
                    int empid = sc.nextInt();
                    boolean status = eservice.deleteById(empid);
                    System.out.println(status ? "Deleted successfully" : "Not found");
                }
                case 8 -> {
                    System.out.println("Enter salary to delete: ");
                    double sal = sc.nextDouble();
                    boolean status = eservice.deleteBySalary(sal);
                    System.out.println(status ? "Deleted successfully" : "Not found");
                }
                case 9 -> {
                    List<Employee> sortedList = eservice.sortBySalary();
                    System.out.println("Employees sorted by salary (ascending):");
                    sortedList.forEach(System.out::println);
                }
                case 10 -> {
                    // write data to file on exit
                    eservice.writeFile(file);
                    System.out.println("Exiting... data saved.");
                }
            }
        } while (choice != 10);
    }
}
