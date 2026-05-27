package com.codveda.level1;

import java.util.Random;
import java.util.Scanner;

public class NumberGuessingGame {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Random rand = new Random();

        int numberToGuess = rand.nextInt(100) + 1; // 1 to 100
        int attempts = 5;

        System.out.println("===== NUMBER GUESSING GAME =====");
        System.out.println("Guess a number between 1 and 100");
        System.out.println("You have " + attempts + " attempts");

        while (attempts > 0) {

            System.out.print("Enter your guess: ");
            int guess = sc.nextInt();

            if (guess == numberToGuess) {
                System.out.println("🎉 Correct! You guessed the number!");
                break;
            } else if (guess > numberToGuess) {
                System.out.println("Too high!");
            } else {
                System.out.println("Too low!");
            }

            attempts--;

            if (attempts > 0) {
                System.out.println("Attempts left: " + attempts);
            } else {
                System.out.println("❌ Game Over! Number was: " + numberToGuess);
            }
        }

        sc.close();
    }
}