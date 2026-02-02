import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    static double balance = 0;

    public static void main(String[] args) throws InterruptedException {
        System.out.println("Welcome to the console ATM!");
        loadBalance();

        printOptions();

        Scanner scanner = new Scanner(System.in);
        int input;

        loop:
        while (true) {
            input = Integer.parseInt(scanner.nextLine());
            switch (input) {
                case 1 -> printBalance();
                case 2 -> depositAmount();
                case 3 -> withdrawAmount();
                case 4 -> {
                    System.out.println("Goodbye!");
                    break loop;
                }
                default -> System.out.println("Invalid operation. Try again.");
            }
            printOptions();
        }
    }

    static void printOptions() {
        System.out.println("\n======================");
        System.out.println("      ATM MENU");
        System.out.println("======================");
        System.out.println("1. Print balance");
        System.out.println("2. Deposit amount");
        System.out.println("3. Withdraw amount");
        System.out.println("4. Exit");
        System.out.println("What would you like to do?");
    }
    static void printBalance() throws InterruptedException {
        System.out.printf("Balance: %.2f%n", balance);
        Thread.sleep(2000);
    }
    static void depositAmount() throws InterruptedException {
        System.out.print("Enter amount to deposit: ");
        Scanner scanner = new Scanner(System.in);
        double amount;
        try {
            amount = Double.parseDouble(scanner.nextLine());
            balance = balance + amount;
            saveBalance();
            System.out.println("Deposit successful!");
            Thread.sleep(2000);
        } catch (Exception exception) {
            System.out.println("Error processing input.");
            Thread.sleep(2000);
        }
    }
    static void withdrawAmount() throws InterruptedException {
        System.out.print("Enter amount to withdraw: ");
        Scanner scanner = new Scanner(System.in);
        double amount;
        try {
            amount = Double.parseDouble(scanner.nextLine());
            if (balance - amount < 0) {
                System.out.println("Not enough funds.");
                Thread.sleep(2000);
            } else {
                balance = balance - amount;
                saveBalance();
                System.out.println("Withdrawal successful!");
                Thread.sleep(2000);
            }
        } catch (Exception exception) {
            System.out.println("Error processing input.");
            Thread.sleep(2000);
        }
    }
    static void loadBalance() {
        File balanceFile = new File("src/balance.txt");
        try (Scanner scanner = new Scanner(balanceFile)) {
            String content = scanner.nextLine();
            balance = Double.parseDouble(content);
        } catch (IOException e) {
            System.out.println("Could not find balance file.");
        }
    }
    static void saveBalance() {
        try (FileWriter balanceFile = new FileWriter("src/balance.txt")) {
            balanceFile.write(String.valueOf(balance));
        } catch (Exception exception) {
            System.out.println("An error occurred during depositing");
        }
    }
}