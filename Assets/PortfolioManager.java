package user_stories.Assets;

import java.io.*;
import java.util.*;

/**
 * The {@code PortfolioManager} class provides static methods to manage a user's asset portfolio.
 * <p>
 * It supports adding, viewing, editing, and removing assets. Data is persisted to a text file.
 * </p>
 * The file used is {@code user_stories/database/assets.txt}.
 * <p>
 * Each asset is stored and loaded using the {@code Asset} class.
 * </p>
 * 
 * This class follows the single-responsibility principle by handling all asset persistence and management logic.
 * 
 * @author 
 */
public class PortfolioManager {
    private static final String FILE_NAME = "user_stories/database/assets.txt";

    /**
     * Loads all assets from the storage file.
     *
     * @return a list of {@code Asset} objects loaded from the file
     * @throws IOException if an I/O error occurs while reading the file
     */
    public static List<Asset> loadAssets() throws IOException {
        List<Asset> assets = new ArrayList<>();
        File file = new File(FILE_NAME);
        if (!file.exists()) file.createNewFile();

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                assets.add(Asset.fromString(line));
            }
        }
        return assets;
    }

    /**
     * Saves a list of assets to the storage file.
     *
     * @param assets the list of assets to save
     * @throws IOException if an I/O error occurs while writing to the file
     */
    public static void saveAssets(List<Asset> assets) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (Asset a : assets) {
                writer.write(a.toFileString());
                writer.newLine();
            }
        }
    }

    /**
     * Adds a new asset to the portfolio.
     *
     * @param name     the asset name
     * @param quantity the asset quantity
     * @param price    the purchase price
     * @param type     the asset type (e.g., Stock, Crypto, Gold, RealEstate)
     * @param date     the purchase date in YYYY-MM-DD format
     * @throws IOException if an I/O error occurs while reading or writing to the file
     */
    public static void addAsset(String name, double quantity, double price, String type, String date) throws IOException {
        List<Asset> assets = loadAssets();
        int newId = assets.isEmpty() ? 1 : assets.get(assets.size() - 1).getId() + 1;
        assets.add(new Asset(newId, name, quantity, price, type, date));
        saveAssets(assets);
        System.out.println("Asset added.");
    }

    /**
     * Displays a list of all assets in the portfolio.
     *
     * @throws IOException if an I/O error occurs while reading the file
     */
    public static void listAssets() throws IOException {
        List<Asset> assets = loadAssets();
        if (assets.isEmpty()) {
            System.out.println("No assets.");
        } else {
            assets.forEach(System.out::println);
        }
    }

    /**
     * Edits an existing asset by its ID.
     * Prompts the user for new values, allowing them to leave inputs empty to keep old values.
     *
     * @param id      the ID of the asset to edit
     * @param scanner the {@code Scanner} object for user input
     * @throws IOException if an I/O error occurs while reading or writing the file
     */
    public static void editAsset(int id, Scanner scanner) throws IOException {
        List<Asset> assets = loadAssets();
        boolean updated = false;

        for (Asset asset : assets) {
            if (asset.getId() == id) {
                System.out.print("New Name (leave empty to keep \"" + asset.name + "\"): ");
                String nameInput = scanner.nextLine();
                String newName = nameInput.isEmpty() ? asset.name : nameInput;

                System.out.print("New Quantity (leave empty to keep " + asset.quantity + "): ");
                String qtyInput = scanner.nextLine();
                double newQty = qtyInput.isEmpty() ? asset.quantity : Double.parseDouble(qtyInput);

                System.out.print("New Price (leave empty to keep " + asset.price + "): ");
                String priceInput = scanner.nextLine();
                double newPrice = priceInput.isEmpty() ? asset.price : Double.parseDouble(priceInput);

                System.out.print("New Type (leave empty to keep \"" + asset.type + "\"): ");
                String typeInput = scanner.nextLine();
                String newType = typeInput.isEmpty() ? asset.type : typeInput;

                System.out.print("New Purchase Date (leave empty to keep \"" + asset.date + "\"): ");
                String dateInput = scanner.nextLine();
                String newDate = dateInput.isEmpty() ? asset.date : dateInput;

                asset.update(newName, newQty, newPrice, newType, newDate);
                updated = true;
                break;
            }
        }

        if (updated) {
            saveAssets(assets);
            System.out.println("Asset updated.");
        } else {
            System.out.println("Asset not found.");
        }
    }

    /**
     * Removes an asset by its ID from the portfolio.
     *
     * @param id the ID of the asset to remove
     * @throws IOException if an I/O error occurs while reading or writing the file
     */
    public static void removeAsset(int id) throws IOException {
        List<Asset> assets = loadAssets();
        boolean removed = assets.removeIf(a -> a.getId() == id);

        if (removed) {
            saveAssets(assets);
            System.out.println("Asset removed.");
        } else {
            System.out.println("Asset not found.");
        }
    }
}
