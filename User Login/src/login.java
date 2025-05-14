import java.io.*;
import java.nio.file.*;
import java.util.*;

/**
 * Main class for the Login functionality of Invest Wise.
 * Handles user login, credential validation, and user authentication.
 */
public class login {

    /**
     * Entry point of the program.
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("==== Welcome to Invest Wise ====");
        System.out.println("==== Login ====");

        handleLogin(input);

        input.close();
    }

    /**
     * Handles the login process by prompting the user for credentials
     * and validating them against stored data.
     *
     * @param input A Scanner object for reading user input.
     */
    public static void handleLogin(Scanner input) {
        while (true) {
            System.out.print("Enter Username: ");
            String username = input.nextLine();

            System.out.print("Enter Password: ");
            String password = input.nextLine();

            if (checkCredentials(username, password)) {
                System.out.println("Login successful! Welcome to your dashboard.\n");
                break;
            } else {
                System.out.println("Invalid username or password. Try again.\n");
            }
        }
    }

    /**
     * Validates an email address using a regular expression.
     *
     * @param email The email address to validate.
     * @return True if the email is valid, false otherwise.
     */
    public static boolean isValidEmail(String email) {
        return email.matches("^[\\w-.]+@[\\w-]+\\.[a-z]{2,}$");
    }

    /**
     * Validates the strength of a password.
     * A strong password must have at least 8 characters, 1 uppercase letter,
     * 1 number, and 1 special character.
     *
     * @param password The password to validate.
     * @return True if the password is strong, false otherwise.
     */
    public static boolean isStrongPassword(String password) {
        return password.length() >= 8 &&
               password.matches(".*[A-Z].*") &&
               password.matches(".*\\d.*") &&
               password.matches(".*[!@#$%^&*()].*");
    }

    /**
     * Checks if a username is unique by verifying it does not already exist
     * in the user storage file.
     *
     * @param username The username to check.
     * @return True if the username is unique, false otherwise.
     */
    public static boolean isUsernameUnique(String username) {
        // path file inside database folder
        Path filePath = Paths.get("database/users.txt");

        try {
            if (!Files.exists(filePath)) Files.createFile(filePath);

            List<String> lines = Files.readAllLines(filePath);
            for (String line : lines) {
                String[] parts = line.split(",");
                if (parts.length > 2 && parts[2].equals(username)) {
                    return false;
                }
            }

            return true;
        } catch (IOException e) {
            System.out.println("Error checking username: " + e.getMessage());
            return false;
        }
    }

    /**
     * Saves a user's data to the user storage file.
     *
     * @param name     The user's name.
     * @param email    The user's email.
     * @param username The user's username.
     * @param password The user's password.
     */
    public static void saveUser(String name, String email, String username, String password) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("p:\\Sophomore_SecTerm\\Software Engineering\\assignment2\\user_stories\\database\\users.txt", true))) {
            writer.write(name + "," + email + "," + username + "," + password);
            writer.newLine();
        } catch (IOException e) {
            System.out.println("Error saving user: " + e.getMessage());
        }
    }

    /**
     * Checks if the provided username and password match any entry
     * in the user storage file.
     *
     * @param username The username to check.
     * @param password The password to check.
     * @return True if the credentials are valid, false otherwise.
     */
    public static boolean checkCredentials(String username, String password) {
        Path filePath = Paths.get("database/users.txt");

        try {
            if (!Files.exists(filePath)) return false;

            List<String> lines = Files.readAllLines(filePath);
            for (String line : lines) {
                line = line.trim();
                String[] parts = line.split(",");
                if (parts.length >= 4) {

                    if (parts[2].trim().equals(username.trim()) &&
                        parts[3].trim().equals(password.trim())) {
                        return true;
                    }
                }
            }

            return false;
        } catch (IOException e) {
            System.out.println("Error reading users file.");
            return false;
        }
    }
}