package com.codveda.level2;

import java.util.ArrayList;
import java.util.Scanner;

// --- CLASS 1: The Employee Data Model (Not public) ---
class Employee {
    private int id;
    private String name;
    private double salary;

    public Employee(int id, String name, double salary) {
        this.id = id;
        this.name = name;
        this.salary = salary;
    }

    public int getId() { return id; }
    public void setName(String name) { this.name = name; }
    public void setSalary(double salary) { this.salary = salary; }

    @Override
    public String toString() {
        return "ID: " + id + " | Name: " + name + " | Salary: $" + String.format("%.2f", salary);
    }
}

// --- CLASS 2: The Main Application (Must be public to match filename) ---
public class EmployeeManagementSystem {
    private static ArrayList<Employee> employeeList = new ArrayList<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int choice;

        System.out.println("--- Codveda Employee Management System ---");

        do {
            System.out.println("\n1. Add Employee (Create)");
            System.out.println("2. View All Employees (Read)");
            System.out.println("3. Update Employee (Update)");
            System.out.println("4. Delete Employee (Delete)");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    addEmployee(sc);
                    break;
                case 2:
                    viewEmployees();
                    break;
                case 3:
                    updateEmployee(sc);
                    break;
                case 4:
                    deleteEmployee(sc);
                    break;
                case 5:
                    System.out.println("Exiting System. Application closed.");
                    break;
                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        } while (choice != 5);

        sc.close();
    }

    private static void addEmployee(Scanner sc) {
        System.out.print("Enter Employee ID: ");
        int id = sc.nextInt();
        sc.nextLine();
        System.out.print("Enter Employee Name: ");
        String name = sc.nextLine();
        System.out.print("Enter Employee Salary: ");
        double salary = sc.nextDouble();

        employeeList.add(new Employee(id, name, salary));
        System.out.println("Employee added successfully!");
    }

    private static void viewEmployees() {
        if (employeeList.isEmpty()) {
            System.out.println("No records found. The system is empty.");
            return;
        }
        System.out.println("\n--- Current Employee Directory ---");
        for (Employee emp : employeeList) {
            System.out.println(emp);
        }
    }

    private static void updateEmployee(Scanner sc) {
        System.out.print("Enter the Employee ID to update: ");
        int id = sc.nextInt();
        sc.nextLine();

        for (Employee emp : employeeList) {
            if (emp.getId() == id) {
                System.out.print("Enter New Name: ");
                emp.setName(sc.nextLine());
                System.out.print("Enter New Salary: ");
                emp.setSalary(sc.nextDouble());
                System.out.println("Employee records updated successfully!");
                return;
            }
        }
        System.out.println("Error: Employee ID not found.");
    }

    private static void deleteEmployee(Scanner sc) {
        System.out.print("Enter the Employee ID to delete: ");
        int id = sc.nextInt();

        for (int i = 0; i < employeeList.size(); i++) {
            if (employeeList.get(i).getId() == id) {
                employeeList.remove(i);
                System.out.println("Employee record removed successfully!");
                return;
            }
        }
        System.out.println("Error: Employee ID not found.");
    }
}