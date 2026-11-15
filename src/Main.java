import model.Student;
import service.StudentManager;
import util.FileHandler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.TreeSet;

/** Console entry point for the College Student Record Management System. */
public class Main {
    private static final String DATA_FILE = "student_records.dat";

    public static void main(String[] args) {
        StudentManager manager = new StudentManager();
        loadRecords(manager);

        try (Scanner scanner = new Scanner(System.in)) {
            int choice;
            do {
                printMenu();
                choice = readInt(scanner, "Enter your choice: ");

                switch (choice) {
                    case 1 -> addStudent(scanner, manager);
                    case 2 -> displayAllStudents(manager);
                    case 3 -> searchStudent(scanner, manager);
                    case 4 -> updateMarks(scanner, manager);
                    case 5 -> displayRankings(manager);
                    case 6 -> saveRecords(manager);
                    case 7 -> loadRecords(manager);
                    case 8 -> System.out.println("Thank you. Goodbye!");
                    default -> System.out.println("Invalid choice. Please choose 1 to 8.");
                }
            } while (choice != 8);
        }
    }

    private static void printMenu() {
        System.out.println("\n==============================================");
        System.out.println("  COLLEGE STUDENT RECORD MANAGEMENT SYSTEM");
        System.out.println("==============================================");
        System.out.println("1. Add student");
        System.out.println("2. Display all students");
        System.out.println("3. Search student by roll number");
        System.out.println("4. Update student marks");
        System.out.println("5. Display student rankings");
        System.out.println("6. Save records");
        System.out.println("7. Load saved records");
        System.out.println("8. Exit");
    }

    private static void addStudent(Scanner scanner, StudentManager manager) {
        int rollNo = readPositiveInt(scanner, "Enter roll number: ");
        System.out.print("Enter student name: ");
        String name = scanner.nextLine().trim();
        while (name.isEmpty()) {
            System.out.println("Name cannot be empty.");
            System.out.print("Enter student name: ");
            name = scanner.nextLine().trim();
        }
        double marks = readMarks(scanner, "Enter marks (0 to 100): ");

        if (manager.addStudent(rollNo, name, marks)) {
            System.out.println("Student added successfully.");
        } else {
            System.out.println("A student with this roll number already exists.");
        }
    }

    private static void displayAllStudents(StudentManager manager) {
        List<Student> students = manager.getAllStudents();
        if (students.isEmpty()) {
            System.out.println("No student records found.");
            return;
        }
        System.out.println("\nAll student records:");
        students.forEach(System.out::println);
    }

    private static void searchStudent(Scanner scanner, StudentManager manager) {
        int rollNo = readPositiveInt(scanner, "Enter roll number to search: ");
        Student student = manager.searchStudent(rollNo);
        if (student == null) {
            System.out.println("Student not found.");
        } else {
            System.out.println("Student found: " + student);
        }
    }

    private static void updateMarks(Scanner scanner, StudentManager manager) {
        int rollNo = readPositiveInt(scanner, "Enter roll number: ");
        double marks = readMarks(scanner, "Enter new marks (0 to 100): ");
        if (manager.updateMarks(rollNo, marks)) {
            System.out.println("Marks updated successfully.");
        } else {
            System.out.println("Student not found.");
        }
    }

    private static void displayRankings(StudentManager manager) {
        TreeSet<Student> rankedStudents = manager.getRankedStudents();
        if (rankedStudents.isEmpty()) {
            System.out.println("No student records found.");
            return;
        }
        System.out.println("\nStudent rankings:");
        int rank = 1;
        for (Student student : rankedStudents) {
            System.out.println(rank++ + ". " + student);
        }
    }

    private static void saveRecords(StudentManager manager) {
        try {
            FileHandler.saveStudents(manager.getAllStudents(), DATA_FILE);
            System.out.println(manager.getStudentCount() + " record(s) saved to " + DATA_FILE + ".");
        } catch (IOException exception) {
            System.out.println("Could not save records: " + exception.getMessage());
        }
    }

    private static void loadRecords(StudentManager manager) {
        try {
            ArrayList<Student> savedStudents = FileHandler.loadStudents(DATA_FILE);
            manager.replaceStudents(savedStudents);
            if (savedStudents.isEmpty()) {
                System.out.println("No saved records found. Starting with an empty list.");
            } else {
                System.out.println(savedStudents.size() + " saved record(s) loaded successfully.");
            }
        } catch (IOException | ClassNotFoundException exception) {
            System.out.println("Could not load records: " + exception.getMessage());
        }
    }

    private static int readInt(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            String value = scanner.nextLine().trim();
            try {
                return Integer.parseInt(value);
            } catch (NumberFormatException exception) {
                System.out.println("Please enter a whole number.");
            }
        }
    }

    private static int readPositiveInt(Scanner scanner, String prompt) {
        int value;
        do {
            value = readInt(scanner, prompt);
            if (value <= 0) {
                System.out.println("Please enter a positive number.");
            }
        } while (value <= 0);
        return value;
    }

    private static double readMarks(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            String value = scanner.nextLine().trim();
            try {
                double marks = Double.parseDouble(value);
                if (marks >= 0 && marks <= 100) {
                    return marks;
                }
                System.out.println("Marks must be between 0 and 100.");
            } catch (NumberFormatException exception) {
                System.out.println("Please enter a valid number.");
            }
        }
    }
}
