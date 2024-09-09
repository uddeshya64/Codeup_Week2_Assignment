/***
 * Assignment: A Java program that can perform Valid Parentheses Combination, Digit Sum Loop, Consecutive Number Summer, Caesar Cipher with Shift Variability and Encoding Challenge with ASCII Conversion.
 * Owner: Uddeshya Patidar
 * Date: 09/09/2024
 */

package practice.java;

import java.util.Scanner;

public class Learn {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean runAgain = true;

        while (runAgain) {
            System.out.print("Enter input for tasks (String or Number): ");
            String input = scanner.nextLine();
            
            System.out.println("Choose a task to perform:");
            System.out.println("1: Valid Parentheses Combination Generator");
            System.out.println("2: Digit Sum Loop");
            System.out.println("3: Consecutive Number Summer");
            System.out.println("4: Caesar Cipher with Shift Variability");
            System.out.println("5: Encoding Challenge with ASCII Conversion");

            int choice = 0;
            // Validate task choice input
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid task choice. Please enter a number between 1 and 5.");
                continue;  
            }

            switch (choice) {
                case 1:
                    System.out.println("All valid combinations:");
                    generateSubstrings(input);
                    break;

                case 2:
                    try {
                        int num = Integer.parseInt(input);
                        digitSumLoop(num);
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid input. Please enter a valid number for Digit Sum Loop.");
                    }
                    break;

                case 3:
                    try {
                        int numConsec = Integer.parseInt(input);
                        System.out.println("All possible consecutive sums for " + numConsec + ":");
                        findConsecutiveSums(numConsec);
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid input. Please enter a valid number for Consecutive Number Summer.");
                    }
                    break;

                case 4:
                    System.out.println("Enter Caesar cipher shift pattern (space-separated integers): ");
                    String[] shiftInput = scanner.nextLine().split(" ");
                    int[] shiftPattern = new int[shiftInput.length];
                    boolean invalidShift = false;
                    for (int i = 0; i < shiftInput.length; i++) {
                        try {
                            shiftPattern[i] = Integer.parseInt(shiftInput[i]);
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid shift pattern. Please enter valid integers.");
                            invalidShift = true;
                            break;
                        }
                    }
                    if (!invalidShift) {
                        caesarCipher(input, shiftPattern);
                    }
                    break;

                case 5:
                    System.out.println("Enter digits array (space-separated single digits): ");
                    String[] digitsInput = scanner.nextLine().split(" ");
                    int[] digits = new int[digitsInput.length];
                    boolean invalidDigits = false;
                    for (int i = 0; i < digitsInput.length; i++) {
                        try {
                            digits[i] = Integer.parseInt(digitsInput[i]);
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid digits. Please enter valid integers.");
                            invalidDigits = true;
                            break;
                        }
                    }

                    if (!invalidDigits) {
                        System.out.println("Enter series array (space-separated integers): ");
                        String[] seriesInput = scanner.nextLine().split(" ");
                        int[] series = new int[seriesInput.length];
                        boolean invalidSeries = false;
                        for (int i = 0; i < seriesInput.length; i++) {
                            try {
                                series[i] = Integer.parseInt(seriesInput[i]);
                            } catch (NumberFormatException e) {
                                System.out.println("Invalid series. Please enter valid integers.");
                                invalidSeries = true;
                                break;
                            }
                        }

                        if (!invalidSeries) {
                            encodeToAscii(digits, series);
                        }
                    }
                    break;

                default:
                    System.out.println("Invalid task choice. Please choose a number between 1 and 5.");
            }
            System.out.print("Do you want to run the program again? (y/n): ");
            String response = scanner.nextLine().trim().toLowerCase();
            if (!response.equals("y")) {
                runAgain = false;
            }
        }

        scanner.close();
        System.out.println("Program terminated.");
    }

    // Task 1: Generate all valid combinations
    public static void generateSubstrings(String input) {
        for (int i = 0; i < input.length(); i++) {
            for (int j = i + 1; j <= input.length(); j++) {
                String substring = input.substring(i, j); 
                generatePermutations(substring.toCharArray(), 0); 
            }
        }
    }
    public static void generatePermutations(char[] input, int index) {
        if (index == input.length - 1) {
            System.out.println(new String(input));
            return;
        }
        for (int i = index; i < input.length; i++) {
            swap(input, index, i);
            generatePermutations(input, index + 1);
            swap(input, index, i); 
        }
    }
    public static void swap(char[] arr, int i, int j) {
        char temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    // Task 2: Digit Sum Loop
    public static void digitSumLoop(int number) {
        while (number > 9) {
            int sum = 0;
            while (number > 0) {
                sum += number % 10;
                number = number / 10;
            }
            number = sum;
        }
        System.out.println("Final single digit: " + number);
    }

    // Task 3: Consecutive Number Summer
    public static void findConsecutiveSums(int number) {
        for (int start = 1; start < number; start++) {
            int sum = 0;
            for (int i = start; i < number; i++) {
                sum += i;
                if (sum == number) {
                    printConsecutiveNumbers(start, i);
                    break;
                } else if (sum > number) {
                    break;
                }
            }
        }
    }

    public static void printConsecutiveNumbers(int start, int end) {
        for (int i = start; i <= end; i++) {
            if (i != start) {
                System.out.print(" + ");
            }
            System.out.print(i);
        }
        System.out.println();
    }

    // Task 4: Caesar Cipher with Shift Variability
    public static void caesarCipher(String input, int[] shiftPattern) {
        int patternLength = shiftPattern.length;
        String result = "";
        for (int i = 0, patternIndex = 0; i < input.length(); i++) {
            char currentChar = input.charAt(i);
            if (currentChar != ' ') {
                int shift = shiftPattern[patternIndex % patternLength];
                char shiftedChar = (char) (currentChar + shift);
                result += shiftedChar;
                patternIndex++;  
            } else {
                result += ' ';
            }
        }
        System.out.println("Encrypted Text: " + result);
    }

    // Task 5: Encoding Challenge with ASCII Conversion
    public static void encodeToAscii(int[] digits, int[] series) {
        int[] highestNumbers = new int[series.length];
        for (int i = 0; i < series.length; i++) {
            int max = -1;
            for (int j = 0; j < digits.length; j++) {
                if (digits[j] > max) {
                    max = digits[j];
                }
            }
            highestNumbers[i] = max;
        }
        for (int i = 0; i < highestNumbers.length; i++) {
            System.out.print((int) highestNumbers[i] + " ");
        }
        System.out.println();
    }
}
