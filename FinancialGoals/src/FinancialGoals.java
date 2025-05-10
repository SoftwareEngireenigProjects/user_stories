import java.io.*;
import java.nio.file.*;
import java.time.LocalDate;
import java.util.*;

/**
 * Main class for managing financial goals in Invest Wise.
 * Provides functionality to add new goals, view existing goals, and save them to a file.
 */
public class FinancialGoals {

    /** Scanner object for reading user input. */
    static Scanner input = new Scanner(System.in);

    /** Path to the file where financial goals are stored. */
    static final String GOALS_FILE = "database/goals.txt";

    /** List of predefined goal types. */
    static final List<String> GOAL_TYPES = Arrays.asList("Retirement", "Wealth Accumulation");

    /**
     * Entry point of the program.
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        System.out.println("\n==== Financial Goals Management ====");

        while (true) {
            System.out.println("\nChoose option:");
            System.out.println("1. Add New Goal");
            System.out.println("2. View Goals");
            System.out.println("3. Exit");
            String choice = input.nextLine();

            switch (choice) {
                case "1":
                    addNewGoal();
                    break;
                case "2":
                    viewGoals();
                    break;
                case "3":
                    System.out.println("Goodbye!");
                    return;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }

    /**
     * Prompts the user to add a new financial goal.
     * Validates user input and saves the goal to the storage file.
     */
    public static void addNewGoal() {
        String type = "";
        while (true) {
            System.out.println("\nAvailable Goal Types:");
            for (int i = 0; i < GOAL_TYPES.size(); i++) {
                System.out.println((i + 1) + ". " + GOAL_TYPES.get(i));
            }

            System.out.print("Enter Goal Type Number (e.g., 1 or 2): ");
            String choiceStr = input.nextLine().trim();

            try {
                int choice = Integer.parseInt(choiceStr);
                if (choice >= 1 && choice <= GOAL_TYPES.size()) {
                    type = GOAL_TYPES.get(choice - 1);
                    break;
                } else {
                    System.out.println("Invalid number. Please choose a number from the list.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }
        }

        System.out.print("Enter Target Amount: ");
        String targetStr = input.nextLine().trim();

        System.out.print("Enter Deadline (YYYY-MM-DD): ");
        String deadlineStr = input.nextLine().trim();

        System.out.print("Enter Current Progress: ");
        String progressStr = input.nextLine().trim();

        if (targetStr.isEmpty() || deadlineStr.isEmpty() || progressStr.isEmpty()) {
            System.out.println("Please fill in all required fields.");
            return;
        }

        try {
            double target = Double.parseDouble(targetStr);
            double progress = Double.parseDouble(progressStr);
            LocalDate deadline = LocalDate.parse(deadlineStr);

            if (target <= 0 || progress < 0) {
                System.out.println("Amounts must be valid numbers (target > 0, progress ≥ 0).");
                return;
            }

            saveGoal(type, target, deadlineStr, progress);
            System.out.println("Goal saved successfully!");

        } catch (Exception e) {
            System.out.println("Invalid input. Please enter valid numbers and date.");
        }
    }

    /**
     * Displays all financial goals stored in the file.
     * Reads the goals from the file and prints them to the console.
     */
    public static void viewGoals() {
        Path path = Paths.get(GOALS_FILE);
        if (!Files.exists(path)) {
            System.out.println("No goals found.");
            return;
        }

        try {
            List<String> lines = Files.readAllLines(path);
            if (lines.isEmpty()) {
                System.out.println("No goals found.");
                return;
            }

            System.out.println("\n==== Your Financial Goals ====\n");
            for (String line : lines) {
                String[] parts = line.split(",");
                if (parts.length == 4) {
                    System.out.println("Type: " + parts[0]);
                    System.out.println("Target Amount: $" + parts[1]);
                    System.out.println("Deadline: " + parts[2]);
                    System.out.println("Current Progress: $" + parts[3]);
                    System.out.println("--------------------------");
                }
            }

        } catch (IOException e) {
            System.out.println("Error reading goals: " + e.getMessage());
        }
    }

    /**
     * Saves a financial goal to the storage file.
     *
     * @param type     The type of the financial goal (e.g., Retirement).
     * @param target   The target amount for the goal.
     * @param deadline The deadline for achieving the goal.
     * @param progress The current progress toward the goal.
     */
    public static void saveGoal(String type, double target, String deadline, double progress) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(GOALS_FILE, true))) {
            writer.write(type + "," + target + "," + deadline + "," + progress);
            writer.newLine();
        } catch (IOException e) {
            System.out.println("Error saving goal: " + e.getMessage());
        }
    }
}