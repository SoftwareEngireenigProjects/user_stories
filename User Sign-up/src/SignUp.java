import java.io.*;
import java.util.Scanner;

/**
 * Main class for the Sign-Up functionality of Invest Wise.
 * Handles user registration, validation, and storage.
 */
public class SignUp {

    /**
     * Entry point of the program.
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        displaySignUpPage();
    }

    /**
     * Displays the sign-up page and handles user input for registration.
     */
    public static void displaySignUpPage() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("==== Welcome to Invest Wise ====");
        System.out.println("==== Sign Up ====");

        // Get user's name
        System.out.print("Enter Name: ");
        String name = scanner.nextLine();

        // Get and validate user's email
        String email;
        while (true) {
            System.out.print("Enter Email: ");
            email = scanner.nextLine();
            if (Validator.isValidEmail(email)) {
                break;
            } else {
                System.out.println("Invalid email. Please try again.");
            }
        }

        // Get and validate unique username
        String username;
        while (true) {
            System.out.print("Enter Username: ");
            username = scanner.nextLine();
            try {
                if (UserStorage.isUsernameUnique(username)) {
                    break;
                } else {
                    System.out.println("Username already exists. Try another one.");
                }
            } catch (IOException e) {
                System.out.println("Error checking username. Try again.");
            }
        }

        // Get and validate strong password
        String password;
        while (true) {
            System.out.print("Enter Password: ");
            password = scanner.nextLine();
            if (Validator.isStrongPassword(password)) {
                break;
            } else {
                System.out.println("Weak password. Use at least 8 chars, 1 capital, 1 number, 1 symbol.");
            }
        }

        // Save user data
        try {
            User user = new User(name, email, username, password);
            UserStorage.saveUser(user);
            System.out.println("Sign-up successful! Redirecting to login...");
        } catch (IOException e) {
            System.out.println("Error saving user.");
        }
    }

    /**
     * Represents a user with name, email, username, and password.
     */
    static class User {
        private String name;
        private String email;
        private String username;
        private String password;

        /**
         * Constructs a new User object.
         *
         * @param name     The user's name.
         * @param email    The user's email.
         * @param username The user's username.
         * @param password The user's password.
         */
        public User(String name, String email, String username, String password) {
            this.name = name;
            this.email = email;
            this.username = username;
            this.password = password;
        }

        /**
         * Converts the user object to a string representation for storage.
         *
         * @return A comma-separated string of user details.
         */
        @Override
        public String toString() {
            return name + "," + email + "," + username + "," + password;
        }
    }

    /**
     * Utility class for validating user input.
     */
    static class Validator {

        /**
         * Validates an email address.
         *
         * @param email The email address to validate.
         * @return True if the email is valid, false otherwise.
         */
        public static boolean isValidEmail(String email) {
            return email.matches("^(.+)@(.+)$");
        }

        /**
         * Validates the strength of a password.
         *
         * @param password The password to validate.
         * @return True if the password is strong, false otherwise.
         */
        public static boolean isStrongPassword(String password) {
            return password.matches("^(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$%^&*]).{8,}$");
        }
    }

    /**
     * Utility class for storing and retrieving user data.
     */
    static class UserStorage {
        private static final String FILE_PATH = "database/users.txt";

        /**
         * Checks if a username is unique.
         *
         * @param username The username to check.
         * @return True if the username is unique, false otherwise.
         * @throws IOException If an error occurs while reading the file.
         */
        public static boolean isUsernameUnique(String username) throws IOException {
            File file = new File(FILE_PATH);
            if (!file.exists()) {
                // If the file doesn't exist, assume the username is unique
                return true;
            }
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.trim().isEmpty()) {
                    // Skip empty lines
                    continue;
                }
                String[] data = line.split(",");
                if (data.length >= 3 && data[2].equals(username)) {
                    reader.close();
                    return false;
                }
            }
            reader.close();
            return true;
        }

        /**
         * Saves a user's data to the storage file.
         *
         * @param user The user to save.
         * @throws IOException If an error occurs while writing to the file.
         */
        public static void saveUser(User user) throws IOException {
            File file = new File(FILE_PATH);
            file.getParentFile().mkdirs(); // Create the directory if it doesn't exist
            BufferedWriter writer = new BufferedWriter(new FileWriter(file, true));
            writer.write(user.toString());
            writer.newLine();
            writer.close();
        }
    }
}