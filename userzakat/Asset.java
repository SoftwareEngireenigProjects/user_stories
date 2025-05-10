package user_stories.userzakat;

/**
 * Represents an asset in the user's portfolio.
 * Each asset has a name, type, investment amount, duration, and allocation percentage.
 */
public class Asset {
    private String name;
    private String type;
    private double amount;
    private int durationInYears;
    private double allocationPercent;

    /**
     * Constructs an Asset object with the specified details.
     *
     * @param name              The name of the asset.
     * @param type              The type of the asset (e.g., cash, gold, stocks).
     * @param amount            The investment amount for the asset.
     * @param durationInYears   The duration of the investment in years.
     * @param allocationPercent The allocation percentage of the asset in the portfolio.
     */
    public Asset(String name, String type, double amount, int durationInYears, double allocationPercent) {
        this.name = name;
        this.type = type;
        this.amount = amount;
        this.durationInYears = durationInYears;
        this.allocationPercent = allocationPercent;
    }

    /**
     * Calculates the Zakat due for the asset based on its type and duration.
     *
     * @return The Zakat amount due for the asset.
     */
    public double calculateZakat() {
        double zakatRate;
        if (type.equalsIgnoreCase("cash") || type.equalsIgnoreCase("gold") ||
            type.equalsIgnoreCase("silver") || type.equalsIgnoreCase("stocks") ||
            type.equalsIgnoreCase("crypto")) {
            zakatRate = 0.025;
        } else {
            zakatRate = 0.0; // No Zakat for types like Real Estate
        }

        if (durationInYears >= 1) {
            return amount * zakatRate;
        } else {
            return 0.0;
        }
    }

    // Getters for the fields (if needed for future use)
    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public double getAmount() {
        return amount;
    }

    public int getDurationInYears() {
        return durationInYears;
    }

    public double getAllocationPercent() {
        return allocationPercent;
    }
}

