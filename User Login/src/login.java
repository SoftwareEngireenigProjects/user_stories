import java.io.*;
import java.nio.file.*;
import java.util.*;

public class login {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("==== Welcome to Invest Wise ====");
        System.out.println("==== Login ====");

        handleLogin(input);

        input.close();
    }


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

    public static boolean isValidEmail(String email) {
        return email.matches("^[\\w-.]+@[\\w-]+\\.[a-z]{2,}$");
    }

    public static boolean isStrongPassword(String password) {
        return password.length() >= 8 &&
               password.matches(".*[A-Z].*") &&
               password.matches(".*\\d.*") &&
               password.matches(".*[!@#$%^&*()].*");
    }

    public static boolean isUsernameUnique(String username) {
        Path filePath = Paths.get("users.txt");

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

    public static void saveUser(String name, String email, String username, String password) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("users.txt", true))) {
            writer.write(name + "," + email + "," + username + "," + password);
            writer.newLine();
        } catch (IOException e) {
            System.out.println("Error saving user: " + e.getMessage());
        }
    }

    public static boolean checkCredentials(String username, String password) {
        Path filePath = Paths.get("database/users.txt");
    
        try {
            if (!Files.exists(filePath)) return false;
    
            List<String> lines = Files.readAllLines(filePath);
            for (String line : lines) {
                line = line.trim();
                String[] parts = line.split(",");
                if (parts.length >= 4) {
                    System.out.println("Checking: username=" + parts[2] + ", password=" + parts[3]);
            
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
