package user_stories.Assets;

/*
 * Represents an individual asset in a user's investment portfolio.
 * Each asset has an ID, name, quantity, price, type, and purchase date.
 */
public class Asset {
    private int id;
    String name;
    double quantity;
    double price;
    String type;
    String date; // Format: YYYY-MM-DD

    /*
     * Constructs an Asset object with specified properties.
     * Parameters:
     * - id: Unique identifier for the asset.
     * - name: Name of the asset.
     * - quantity: Quantity held.
     * - price: Price per unit.
     * - type: Asset category (e.g., Stock, Gold).
     * - date: Purchase date in YYYY-MM-DD format.
     */
    public Asset(int id, String name, double quantity, double price, String type, String date) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.price = price;
        this.type = type;
        this.date = date;
    }

    /*
     * Returns the asset's unique identifier.
     */
    public int getId() {
        return id;
    }

    /*
     * Updates the asset's details with new values.
     * Parameters:
     * - name: New name.
     * - quantity: New quantity.
     * - price: New price.
     * - type: New asset type.
     * - date: New purchase date.
     */
    public void update(String name, double quantity, double price, String type, String date) {
        this.name = name;
        this.quantity = quantity;
        this.price = price;
        this.type = type;
        this.date = date;
    }

    /*
     * Converts the asset's details to a string format suitable for file storage.
     * Fields are separated by pipe (|) characters.
     */
    public String toFileString() {
        return id + "|" + name + "|" + quantity + "|" + price + "|" + type + "|" + date;
    }

    /*
     * Returns a formatted string representing the asset, used for display.
     */
    @Override
    public String toString() {
        return "ID: " + id + " | Type: " + type + " | Name: " + name +
               " | Qty: " + quantity + " | Price: $" + price + " | Date: " + date;
    }

    /*
     * Parses a string from the file and constructs an Asset object.
     * Assumes fields are separated by pipe (|) characters.
     * Returns a new Asset object.
     */
    public static Asset fromString(String line) {
        String[] parts = line.split("\\|");
        return new Asset(
            Integer.parseInt(parts[0]),
            parts[1],
            Double.parseDouble(parts[2]),
            Double.parseDouble(parts[3]),
            parts[4],
            parts[5]
        );
    }
}
