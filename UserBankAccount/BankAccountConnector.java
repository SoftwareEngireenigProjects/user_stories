package user_stories.UserBankAccount;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

/**
 * This class handles the process of connecting a bank account by validating
 * user input, checking card details against a database, and verifying OTP.
 */
public class BankAccountConnector {

    /**
     * Validates the card details against the database.
     *
     * @param cardNumber The card number to validate.
     * @param name       The cardholder's name.
     * @param expiry     The expiry date of the card (MM/YY).
     * @param cvv        The CVV of the card.
     * @return true if the card details are valid and found in the database, false otherwise.
     */
    public static boolean isValidCard(String cardNumber, String name, String expiry, String cvv) {
        try (BufferedReader br = new BufferedReader(new FileReader("p:\\Sophomore_SecTerm\\Software Engineering\\assignment2\\user_stories\\database\\bank_accounts.csv"))) {
            String line;
            br.readLine(); // Skip header
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 4 &&
                        parts[0].trim().equals(cardNumber) &&
                        parts[1].trim().equalsIgnoreCase(name) &&
                        parts[2].trim().equals(expiry) &&
                        parts[3].trim().equals(cvv)) {
                    return true;
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading bank database: " + e.getMessage());
        }
        return false;
    }

    /**
     * Validates the format of the card details.
     *
     * @param cardNumber The card number to validate.
     * @param name       The cardholder's name.
     * @param expiry     The expiry date of the card (MM/YY).
     * @param cvv        The CVV of the card.
     * @return true if all inputs are in the correct format, false otherwise.
     */
    public static boolean isValidFormat(String cardNumber, String name, String expiry, String cvv) {
        return cardNumber.matches("\\d{16}") &&
                name.matches("[a-zA-Z ]+") &&
                expiry.matches("(0[1-9]|1[0-2])/\\d{2}") &&
                cvv.matches("\\d{3}");
    }

    /**
     * The main method that drives the program. It handles user input, validates
     * card details, and verifies OTP for connecting a bank account.
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("=== Connect Bank Account ===");

        while (true) {
            // Step 1: Bank selection
            int bankChoice = 0;
            while (true) {
                System.out.println("Select your bank:");
                System.out.println("1. Bank A\n2. Bank B\n3. Bank C");
                System.out.print("Enter your choice: ");
                if (sc.hasNextInt()) {
                    bankChoice = sc.nextInt();
                    sc.nextLine(); // Consume the newline character
                    if (bankChoice >= 1 && bankChoice <= 3) {
                        break; // Valid choice, exit the loop
                    } else {
                        System.out.println("Invalid choice. Please select 1, 2, or 3.");
                    }
                } else {
                    System.out.println("Invalid input. Please enter a number.");
                    sc.next(); // Consume the invalid input
                }
            }

            String cardNumber, name, expiry, cvv;

            // Input + validation loop
            while (true) {
                System.out.print("Enter card number (16 digits): ");
                cardNumber = sc.nextLine().trim();
                if (!cardNumber.matches("\\d{16}")) {
                    System.out.println("Card number must be exactly 16 digits.");
                    continue;
                }

                System.out.print("Enter card holder name (in English letters): ");
                name = sc.nextLine().trim();
                if (!name.matches("[a-zA-Z ]+")) {
                    System.out.println("Name must contain English letters only.");
                    continue;
                }

                System.out.print("Enter expiry date (MM/YY): ");
                expiry = sc.nextLine().trim();
                if (!expiry.matches("(0[1-9]|1[0-2])/\\d{2}")) {
                    System.out.println("Expiry must be in MM/YY format.");
                    continue;
                }

                System.out.print("Enter CVV (3 digits): ");
                cvv = sc.nextLine().trim();
                if (!cvv.matches("\\d{3}")) {
                    System.out.println("CVV must be 3 digits.");
                    continue;
                }

                // Database check
                if (!isValidCard(cardNumber, name, expiry, cvv)) {
                    System.out.println("Card details not found in the system.");
                    continue;
                }

                break;
            }

            // OTP
            Random rand = new Random();
            int otp = 100000 + rand.nextInt(900000);
            System.out.println("📨 OTP sent: " + otp);

            int attempts = 0;
            boolean verified = false;
            while (attempts < 3) {
                System.out.print("Enter OTP: ");
                String otpInput = sc.nextLine().trim();
                if (otpInput.matches("\\d{6}") && Integer.parseInt(otpInput) == otp) {
                    verified = true;
                    break;
                } else {
                    System.out.println("Incorrect OTP.");
                    attempts++;
                }
            }

            if (verified) {
                System.out.println("Bank account linked successfully!");
            } else {
                System.out.println("Failed to verify OTP.");
            }

            System.out.print("Do you want to link another account? (yes/no): ");
            String again = sc.nextLine().trim().toLowerCase();
            if (!again.equals("yes")) {
                System.out.println("Exiting. Thank you!");
                break;
            }
        }

        sc.close();
    }
}
