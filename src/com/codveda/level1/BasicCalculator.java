package com.codveda.level1;

import java.util.Scanner;

// Calculator class with operations
class Calculator {

    public double add(double a, double b) {
        return a + b;
    }

    public double subtract(double a, double b) {
        return a - b;
    }

    public double multiply(double a, double b) {
        return a * b;
    }

    public double divide(double a, double b) {
        if (b == 0) {
            System.out.println("Error: Division by zero is not allowed!");
            return 0;
        }
        return a / b;
    }
}

// Main class
public class BasicCalculator {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Calculator calc = new Calculator();

        System.out.println("===== BASIC CALCULATOR =====");

        System.out.print("Enter first number: ");
        double num1 = sc.nextDouble();

        System.out.print("Enter second number: ");
        double num2 = sc.nextDouble();

        System.out.println("\nChoose operation:");
        System.out.println("1. Addition (+)");
        System.out.println("2. Subtraction (-)");
        System.out.println("3. Multiplication (*)");
        System.out.println("4. Division (/)");

        System.out.print("Enter choice (1-4): ");
        int choice = sc.nextInt();

        double result;

        switch (choice) {

            case 1:
                result = calc.add(num1, num2);
                System.out.println("Result: " + result);
                break;

            case 2:
                result = calc.subtract(num1, num2);
                System.out.println("Result: " + result);
                break;

            case 3:
                result = calc.multiply(num1, num2);
                System.out.println("Result: " + result);
                break;

            case 4:
                result = calc.divide(num1, num2);
                System.out.println("Result: " + result);
                break;

            default:
                System.out.println("Invalid choice!");
        }

        sc.close();
    }
}