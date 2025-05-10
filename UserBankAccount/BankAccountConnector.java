package user_stories.UserBankAccount;
import java.util.Random;
import java.util.Scanner;

public class BankAccountConnector {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("=== Connect Bank Account ===");

        // Step 1: Bank selection
        System.out.println("Select your bank:");
        System.out.println("1. Bank A\n2. Bank B\n3. Bank C");
        int bankChoice = sc.nextInt();
        sc.nextLine(); // Consume newline

        // Step 2: Card input
        System.out.print("Enter card number (16 digits): ");
        String cardNumber = sc.nextLine();
        System.out.print("Enter card holder name: ");
        String name = sc.nextLine();
        System.out.print("Enter expiry date (MM/YY): ");
        String expiry = sc.nextLine();
        System.out.print("Enter CVV (3 digits): ");
        String cvv = sc.nextLine();

        // Validate simple input rules
        if (cardNumber.length() != 16 || cvv.length() != 3) {
            System.out.println("Invalid card details.");
            return;
        }

        // Step 3: Simulate OTP
        Random rand = new Random();
        int otp = 100000 + rand.nextInt(900000);
        System.out.println("OTP sent: " + otp); // simulate sending

        System.out.print("Enter OTP: ");
        int enteredOtp = sc.nextInt();

        if (enteredOtp == otp) {
            System.out.println(" Bank account linked successfully!");
        } else {
            System.out.println(" Invalid OTP. Please try again.");
        }

        sc.close();
    }
}
