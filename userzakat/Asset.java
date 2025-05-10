package user_stories.userzakat;

public class Asset {
    String name;
    String type;
    double amount;
    int durationInYears;
    double allocationPercent;

    public Asset(String name, String type, double amount, int durationInYears, double allocationPercent) {
        this.name = name;
        this.type = type;
        this.amount = amount;
        this.durationInYears = durationInYears;
        this.allocationPercent = allocationPercent;
    }

    public double calculateZakat() {
        double zakatRate;
        if (type.equalsIgnoreCase("cash") || type.equalsIgnoreCase("gold") || 
            type.equalsIgnoreCase("silver") || type.equalsIgnoreCase("stocks") || 
            type.equalsIgnoreCase("crypto")) {
            zakatRate = 0.025;
        } else {
            zakatRate = 0.0; // for types like Real Estate
        }

        if (durationInYears >= 1) {
            return amount * zakatRate;
        } else {
            return 0.0;
        }
    }
}

