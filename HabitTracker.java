import java.util.*;

class Habit {
    String name;
    boolean[] completion = new boolean[7];

    Habit(String name) {
        this.name = name;
    }

    void markDone(int dayIndex) {
        if (dayIndex >= 0 && dayIndex < 7) {
            completion[dayIndex] = true;
        } else {
            System.out.println("Invalid day index! Please enter between 0–6.");
        }
    }

    void showProgress() {
        System.out.println("\nHabit: " + name);
        String[] days = {"Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"};
        for (int i = 0; i < 7; i++) {
            System.out.println(days[i] + ": " + (completion[i] ? "✅" : "❌"));
        }
    }

    int getTotal() {
        int count = 0;
        for (boolean done : completion) if (done) count++;
        return count;
    }
}

public class HabitTracker {
    static Scanner scanner = new Scanner(System.in);
    static ArrayList<Habit> habits = new ArrayList<>();

    public static void main(String[] args) {
        int choice = 0;
        while (choice != 4) {
            System.out.println("\n============== Personal Habit Tracker ============");
            System.out.println("1. Add Habit");
            System.out.println("2. Mark Habit as Done");
            System.out.println("3. View Habit Report");
            System.out.println("4. Exit");
            System.out.print("Choice: ");

            // Prevent crash on non-integer input
            if (!scanner.hasNextInt()) {
                System.out.println("Please enter a valid number.");
                scanner.nextLine();
                continue;
            }

            choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1 -> addHabit();
                case 2 -> markHabit();
                case 3 -> showReport();
                case 4 -> System.out.println("Goodbye!");
                default -> System.out.println("Invalid option. Try again.");
            }
        }
    }

    static void addHabit() {
        System.out.print("Enter habit name: ");
        String name = scanner.nextLine().trim();
        if (name.isEmpty()) {
            System.out.println("Habit name cannot be empty.");
            return;
        }
        habits.add(new Habit(name));
        System.out.println("Habit added successfully!");
    }

    static void markHabit() {
        if (habits.isEmpty()) {
            System.out.println("No habits to mark yet.");
            return;
        }

        showHabits();
        System.out.print("Select habit number: ");

        if (!scanner.hasNextInt()) {
            System.out.println("Invalid input!");
            scanner.nextLine();
            return;
        }

        int index = scanner.nextInt() - 1;

        System.out.print("Enter day index (0=Sun ... 6=Sat): ");

        if (!scanner.hasNextInt()) {
            System.out.println("Invalid input!");
            scanner.nextLine();
            return;
        }

        int day = scanner.nextInt();

        if (index >= 0 && index < habits.size()) {
            habits.get(index).markDone(day);
            System.out.println("Marked as done for that day!");
        } else {
            System.out.println("Invalid habit number.");
        }
    }

    static void showReport() {
        if (habits.isEmpty()) {
            System.out.println("No habits tracked yet.");
            return;
        }

        System.out.println("\n========== Weekly Habit Report ==========");
        for (Habit habit : habits) {
            habit.showProgress();
            System.out.println("Completion: " + habit.getTotal() + "/7 days\n");
        }
    }

    static void showHabits() {
        System.out.println("\nTracked Habits:");
        for (int i = 0; i < habits.size(); i++) {
            System.out.println((i + 1) + ". " + habits.get(i).name);
        }
    }
}
