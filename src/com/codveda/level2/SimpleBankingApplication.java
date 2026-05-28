package com.codveda.level2;

import java.util.Scanner;

public class SimpleBankingApplication {
    // We store the balance right here in the class
    private static double balance = 0.0;

    public static void main(String[] Object) {
        Scanner sc = new Scanner(System.in);
        int choice;

        System.out.println("--- Welcome to Codveda Bank ---");

        do {
            System.out.println("\n1. Check Balance");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    checkBalance();
                    break;
                case 2:
                    System.out.print("Enter deposit amount: ");
                    double depositAmount = sc.nextDouble();
                    deposit(depositAmount);
                    break;
                case 3:
                    System.out.print("Enter withdrawal amount: ");
                    double withdrawAmount = sc.nextDouble();
                    withdraw(withdrawAmount);
                    break;
                case 4:
                    System.out.println("Thank you for banking with us!");
                    break;
                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        } while (choice != 4);

        sc.close();
    }

    // 1. Method to check balance
    public static void checkBalance() {
        System.out.printf("Your current balance is: $%.2f\n", balance);
    }

    // 2. Method to deposit money
    public static void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.printf("Successfully deposited $%.2f\n", amount);
        } else {
            System.out.println("Invalid amount. Deposit must be greater than zero.");
        }
    }

    // 3. Method to withdraw money with basic error handling
    public static void withdraw(double amount) {
        if (amount <= 0) {
            System.out.println("Invalid amount. Withdrawal must be greater than zero.");
        } else if (amount > balance) {
            System.out.println("Transaction Failed: Insufficient funds!");
        } else {
            balance -= amount;
            System.out.printf("Successfully withdrew $%.2f\n", amount);
        }
    }
}