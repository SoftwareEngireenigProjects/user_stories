import java.io.*;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        displaySignUpPage();
    }

    public static void displaySignUpPage() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("==== Sign Up ====");
    
        System.out.print("Enter Name: ");
        String name = scanner.nextLine();
    
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
    
        // save user data
        try {
            User user = new User(name, email, username, password);
            UserStorage.saveUser(user);
            System.out.println("Sign-up successful! Redirecting to login...");
        } catch (IOException e) {
            System.out.println("Error saving user.");
        }
    }
    

    // --- Class: User ---
    static class User {
        private String name, email, username, password;

        public User(String name, String email, String username, String password) {
            this.name = name;
            this.email = email;
            this.username = username;
            this.password = password;
        }

        @Override
        public String toString() {
            return name + "," + email + "," + username + "," + password;
        }
    }

    // --- Class: Validator ---
    static class Validator {
        public static boolean isValidEmail(String email) {
            return email.matches("^(.+)@(.+)$");
        }

        public static boolean isStrongPassword(String password) {
            return password.matches("^(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$%^&*]).{8,}$");
        }
    }

    // --- Class: UserStorage ---
    static class UserStorage {
        private static final String FILE_PATH = "database/users.txt";

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
