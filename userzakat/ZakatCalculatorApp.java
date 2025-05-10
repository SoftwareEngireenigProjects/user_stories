package user_stories.userzakat;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * A console-based application for calculating Zakat on a user's portfolio of assets.
 * The application prompts the user to input details about their assets and calculates
 * the total Zakat due based on the type and duration of each asset.
 */
public class ZakatCalculatorApp {

    /**
     * The main method that drives the Zakat Calculator application.
     * It collects user input, calculates Zakat for each asset, and displays the total Zakat due.
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<Asset> assets = new ArrayList<>();

        System.out.println("=== Zakat Calculator ===");
        System.out.print("Enter number of assets in portfolio: ");
        while (!sc.hasNextInt()) {
            System.out.println("Invalid input. Please enter a valid number.");
            sc.next(); // Consume invalid input
        }
        int n = sc.nextInt();
        sc.nextLine(); // Consume newline

        double totalZakat = 0;

        for (int i = 0; i < n; i++) {
            System.out.println("\nAsset #" + (i + 1));
            System.out.print("Asset Name: ");
            String name = sc.nextLine();

            System.out.print("Asset Type (cash, stocks, gold, crypto, real estate): ");
            String type = sc.nextLine();

            System.out.print("Investment Amount: ");
            while (!sc.hasNextDouble()) {
                System.out.println("Invalid input. Please enter a valid amount.");
                sc.next(); // Consume invalid input
            }
            double amount = sc.nextDouble();

            System.out.print("Duration (in years): ");
            while (!sc.hasNextInt()) {
                System.out.println("Invalid input. Please enter a valid number of years.");
                sc.next(); // Consume invalid input
            }
            int duration = sc.nextInt();

            System.out.print("Allocation Percentage: ");
            while (!sc.hasNextDouble()) {
                System.out.println("Invalid input. Please enter a valid percentage.");
                sc.next(); // Consume invalid input
            }
            double percent = sc.nextDouble();
            sc.nextLine(); // Consume newline

            Asset asset = new Asset(name, type, amount, duration, percent);
            assets.add(asset);

            double zakat = asset.calculateZakat();
            System.out.printf("Zakat Due for %s: %.2f\n", name, zakat);
            totalZakat += zakat;
        }

        System.out.println("\n=============================");
        System.out.printf("Total Zakat Due: %.2f\n", totalZakat);

        sc.close(); // Close the scanner to prevent resource leaks
    }
}

