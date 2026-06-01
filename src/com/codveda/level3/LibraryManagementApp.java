package com.codveda.level3;

import java.sql.*;
import java.util.Scanner;

public class LibraryManagementApp {

    // Connection URL for a local, file-based SQLite database
    private static final String DB_URL = "jdbc:sqlite:library.db";

    public static void main(String[] args) {
        // 1. Initialize Database and Create Tables
        initializeDatabase();

        Scanner scanner = new Scanner(System.in);
        System.out.println("--- Library Management System (JDBC) ---");

        while (true) {
            System.out.println("\n1. Add Book");
            System.out.println("2. Borrow Book");
            System.out.println("3. Return Book");
            System.out.println("4. View All Books");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter book title: ");
                    String title = scanner.nextLine();
                    System.out.print("Enter author name: ");
                    String author = scanner.nextLine();
                    addBook(title, author);
                    break;
                case 2:
                    System.out.print("Enter Book ID to borrow: ");
                    int borrowId = scanner.nextInt();
                    borrowBook(borrowId);
                    break;
                case 3:
                    System.out.print("Enter Book ID to return: ");
                    int returnId = scanner.nextInt();
                    returnBook(returnId);
                    break;
                case 4:
                    viewAllBooks();
                    break;
                case 5:
                    System.out.println("Exiting system. Goodbye!");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }

    // --- JDBC OPERATION: Initialize Database Tables ---
    private static void initializeDatabase() {
        try (Connection conn = DriverManager.getConnection(DB_URL);
             Statement stmt = conn.createStatement()) {

            // Create Books Table if it doesn't exist
            String createBooksTable = "CREATE TABLE IF NOT EXISTS books (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "title TEXT NOT NULL, " +
                    "author TEXT NOT NULL, " +
                    "is_available INTEGER DEFAULT 1" + // 1 = true, 0 = false
                    ");";
            stmt.execute(createBooksTable);

        } catch (SQLException e) {
            System.out.println("Database initialization error: " + e.getMessage());
        }
    }

    // --- JDBC OPERATION: Add a New Book (CRUD: Create) ---
    private static void addBook(String title, String author) {
        String sql = "INSERT INTO books(title, author) VALUES(?, ?)";

        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, title);
            pstmt.setString(2, author);
            pstmt.executeUpdate();
            System.out.println("Book \"" + title + "\" added successfully!");

        } catch (SQLException e) {
            System.out.println("Error adding book: " + e.getMessage());
        }
    }

    // --- JDBC OPERATION: Borrow a Book (CRUD: Update) ---
    private static void borrowBook(int bookId) {
        String checkSql = "SELECT is_available FROM books WHERE id = ?";
        String updateSql = "UPDATE books SET is_available = 0 WHERE id = ?";

        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement checkStmt = conn.prepareStatement(checkSql);
             PreparedStatement updateStmt = conn.prepareStatement(updateSql)) {

            checkStmt.setInt(1, bookId);
            ResultSet rs = checkStmt.executeQuery();

            if (rs.next()) {
                int isAvailable = rs.getInt("is_available");
                if (isAvailable == 1) {
                    updateStmt.setInt(1, bookId);
                    updateStmt.executeUpdate();
                    System.out.println("Success! You have borrowed the book.");
                } else {
                    System.out.println("Sorry, this book is already borrowed.");
                }
            } else {
                System.out.println("Book ID not found.");
            }

        } catch (SQLException e) {
            System.out.println("Error borrowing book: " + e.getMessage());
        }
    }

    // --- JDBC OPERATION: Return a Book (CRUD: Update) ---
    private static void returnBook(int bookId) {
        String updateSql = "UPDATE books SET is_available = 1 WHERE id = ?";

        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement pstmt = conn.prepareStatement(updateSql)) {

            pstmt.setInt(1, bookId);
            int rowsAffected = pstmt.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Success! Book returned to the library.");
            } else {
                System.out.println("Book ID not found.");
            }

        } catch (SQLException e) {
            System.out.println("Error returning book: " + e.getMessage());
        }
    }

    // --- JDBC OPERATION: View All Books (CRUD: Read) ---
    private static void viewAllBooks() {
        String sql = "SELECT * FROM books";

        try (Connection conn = DriverManager.getConnection(DB_URL);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            System.out.println("\n--- Library Catalog ---");
            System.out.printf("%-5s %-30s %-20s %-12s\n", "ID", "Title", "Author", "Status");
            System.out.println("----------------------------------------------------------------------");

            while (rs.next()) {
                int id = rs.getInt("id");
                String title = rs.getString("title");
                String author = rs.getString("author");
                String status = rs.getInt("is_available") == 1 ? "Available" : "Borrowed";

                System.out.printf("%-5d %-30s %-20s %-12s\n", id, title, author, status);
            }

        } catch (SQLException e) {
            System.out.println("Error fetching books: " + e.getMessage());
        }
    }
}