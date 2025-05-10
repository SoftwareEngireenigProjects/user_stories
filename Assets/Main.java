package user_stories.Assets;

import java.io.IOException;
import java.util.Scanner;

/**
 * Main class for the Asset Manager application.
 * Provides a console-based interface to allow users to:
 * - Add an asset
 * - View assets
 * - Edit an asset
 * - Remove an asset
 * Uses the PortfolioManager class to perform asset-related operations.
 * 
 * @author Mahmoud Hosny
 */
public class Main {

    /**
     * The entry point of the application.
     * Displays a menu to the user and performs actions based on user input.
     * 
     * @param args the command-line arguments (not used)
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\nAsset Manager");
            System.out.println("1. Add Asset");
            System.out.println("2. View Assets");
            System.out.println("3. Edit Asset");
            System.out.println("4. Remove Asset");
            System.out.println("0. Exit");
            System.out.print("Choice: ");
            choice = sc.nextInt();
            sc.nextLine(); // Clear newline character from input buffer

            try {
                switch (choice) {
                    case 1 -> {
                        System.out.print("Asset Name: ");
                        String name = sc.nextLine();
                        System.out.print("Quantity: ");
                        double qty = sc.nextDouble();
                        System.out.print("Price: ");
                        double price = sc.nextDouble();
                        sc.nextLine(); // Consume leftover newline
                        System.out.print("Asset Type (Stock, Crypto, Gold, RealEstate): ");
                        String type = sc.nextLine();
                        System.out.print("Purchase Date (YYYY-MM-DD): ");
                        String date = sc.nextLine();
                        PortfolioManager.addAsset(name, qty, price, type, date);
                    }
                    case 2 -> PortfolioManager.listAssets();
                    case 3 -> {
                        PortfolioManager.listAssets();
                        System.out.print("Enter Asset ID to edit: ");
                        int id = sc.nextInt();
                        sc.nextLine();
                        PortfolioManager.editAsset(id, sc);
                    }
                    case 4 -> {
                        PortfolioManager.listAssets();
                        System.out.print("Enter Asset ID to remove: ");
                        int id = sc.nextInt();
                        PortfolioManager.removeAsset(id);
                    }
                    case 0 -> System.out.println("Goodbye!");
                    default -> System.out.println("Invalid choice.");
                }
            } catch (IOException e) {
                System.err.println("File error: " + e.getMessage());
            } catch (NumberFormatException e) {
                System.err.println("Invalid input. Please enter numbers where expected.");
            }
        } while (choice != 0);
    }
}
