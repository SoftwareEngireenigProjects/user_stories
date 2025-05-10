package user_stories.userzakat;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ZakatCalculatorApp {
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
        sc.nextLine(); // consume newline

        double totalZakat = 0;

        for (int i = 0; i < n; i++) {
            System.out.println("\nAsset #" + (i + 1));
            System.out.print("Asset Name: ");
            String name = sc.nextLine();

            System.out.print("Asset Type (cash, stocks, gold, crypto, real estate): ");
            String type = sc.nextLine();

            System.out.print("Investment Amount: ");
            double amount = sc.nextDouble();

            System.out.print("Duration (in years): ");
            int duration = sc.nextInt();

            System.out.print("Allocation Percentage: ");
            double percent = sc.nextDouble();
            sc.nextLine(); // consume newline

            Asset asset = new Asset(name, type, amount, duration, percent);
            assets.add(asset);

            double zakat = asset.calculateZakat();
            System.out.printf("Zakat Due for %s: %.2f\n", name, zakat);
            totalZakat += zakat;
        }

        System.out.println("\n=============================");
        System.out.printf("Total Zakat Due: %.2f\n", totalZakat);
    }
}

