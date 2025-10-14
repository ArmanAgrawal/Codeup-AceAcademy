
import java.util.Scanner;

/**
 * @filename - Main.java
 * @description - Starting Point of My code
 * @author - Arman Agrawal
 */

public class Main {

    public static void main(String[] args) {
        Scanner userInput = new Scanner(System.in);
        int choice = -1;
        StringAndNumber operations = null;

        System.out.println("What do you want to input first?");
        System.out.println("1. String\n2. Number");
        int inputType = 0;
        boolean valid;
        do {
            System.out.println("Enter 1 for String or 2 for Number:");
            if (!userInput.hasNextInt()) {
                System.out.println("Invalid choice. Please enter a number.");
                userInput.nextLine();
                valid = false;
                continue;
            }
            inputType = userInput.nextInt();
            userInput.nextLine();
            if (inputType == 1 || inputType == 2) {
                valid = true; // correct input
            } else {
                System.out.println("Invalid choice. Please enter 1 or 2.");
                valid = false;
            }
        } while (!valid);


        if (inputType == 1) {
            System.out.print("Enter your string: ");
            String str = userInput.nextLine();
            operations = new StringAndNumber(str);
        } else {
            System.out.print("Enter your number: ");
            while (!userInput.hasNextInt()) {
                System.out.println("Invalid number. Enter again:");
                userInput.nextLine();
            }
            int num = userInput.nextInt();
            userInput.nextLine();
            operations = new StringAndNumber(num);
        }


        do {
            System.out.println("""
                    Choose operation:
                    1. Count Unique Palindromes
                    2. Fibonacci Sequence – Nth Number
                    3. Snake Case to Camel Case Conversion
                    4. Count Consonants in a String
                    5. Binary to Decimal Conversion
                    6. Characters in a String
                    7. Character Frequency in a String
                    8. Prime Number Checker
                    9. Number to Words Converter
                    10. Longest Substring Without Repeating Characters
                    0. Exit
                    """);
            System.out.print("Enter your choice: ");
            if (userInput.hasNextInt()) {
                choice = userInput.nextInt();
                userInput.nextLine();
            } else {
                System.out.println("Invalid Choice");
                userInput.nextLine();
                continue;
            }

            switch (choice) {
                case 1:
                    int palindromeCount = operations.countUniquePalindrome();
                    System.out.println(palindromeCount == -1 ? "Invalid Input" :
                            "Unique Palindromic Count: " + palindromeCount);
                    break;

                case 2:
                    int fibonacciNumber = operations.getFibonacciNumber();
                    System.out.println(fibonacciNumber == -1 ? "Invalid Input" : "Nth Fibonacci number: " + fibonacciNumber);
                    break;

                case 3:
                   String camelCaseString = operations.snakeToCamel();
                    System.out.println(camelCaseString);
                    break;

                case 4:
                    int consonants = operations.countConsonants();
                    System.out.println(consonants == -1 ? "Invalid Input" : "Consonant Count: " + consonants);
                    break;
                case 5:
                    int decimalNumber = operations.binaryToDecimal();
                    System.out.println(decimalNumber == -1 ? "Invalid Input" : "Decimal Equivalent: " + decimalNumber);
                    break;
                case 6:
                     String expanded = operations.expandCharacters();
                    System.out.println(expanded);
                    break;
                case 7:
                    String frequency = operations.characterFrequency();
                    System.out.println(frequency);
                    break;
                case 8:
                    String prime = operations.isPrime();
                    System.out.println(prime);
                    break;
                case 9:
                    String words = operations.numberToWords();
                    System.out.println(words);
                    break;

                case 10:
                    int maxLength = operations.longestUniqueSubstring();
                    System.out.println(maxLength == -1 ? "Invalid Input" :
                            "Length of longest substring without repeating characters: " + maxLength);
                    break;
                case 0:
                    System.out.println("Exiting the program. Thank you!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }

        } while (choice != 0);
    }
}