package com.studentmgmt.ui;

import com.studentmgmt.model.Student;
import com.studentmgmt.service.StudentService;
import com.studentmgmt.util.InputValidator;
import java.util.List;
import java.util.Scanner;

/**
 * Console-based user interface for Student Management System
 */
public class ConsoleUI {
    
    private StudentService studentService;
    private Scanner scanner;
    
    public ConsoleUI() {
        this.studentService = new StudentService();
        this.scanner = new Scanner(System.in);
    }
    
    /**
     * Start the application
     */
    public void start() {
        System.out.println("╔════════════════════════════════════════════════════╗");
        System.out.println("║    WELCOME TO STUDENT MANAGEMENT SYSTEM           ║");
        System.out.println("╚════════════════════════════════════════════════════╝");
        
        boolean running = true;
        while (running) {
            displayMenu();
            int choice = getIntInput("Enter your choice: ");
            
            switch (choice) {
                case 1:
                    addStudent();
                    break;
                case 2:
                    viewAllStudents();
                    break;
                case 3:
                    searchStudent();
                    break;
                case 4:
                    updateStudent();
                    break;
                case 5:
                    deleteStudent();
                    break;
                case 6:
                    displayStatistics();
                    break;
                case 7:
                    running = false;
                    System.out.println("\n✓ Thank you for using Student Management System!");
                    System.out.println("✓ Goodbye!\n");
                    break;
                default:
                    System.out.println("\n✗ Invalid choice. Please try again.\n");
            }
        }
        
        scanner.close();
    }
    
    /**
     * Display main menu
     */
    private void displayMenu() {
        System.out.println("\n┌────────────────────────────────────────────────────┐");
        System.out.println("│                    MAIN MENU                       │");
        System.out.println("├────────────────────────────────────────────────────┤");
        System.out.println("│  1. Add New Student                                │");
        System.out.println("│  2. View All Students                              │");
        System.out.println("│  3. Search Student                                 │");
        System.out.println("│  4. Update Student                                 │");
        System.out.println("│  5. Delete Student                                 │");
        System.out.println("│  6. Display Statistics                             │");
        System.out.println("│  7. Exit                                           │");
        System.out.println("└────────────────────────────────────────────────────┘");
    }
    
    /**
     * Add a new student
     */
    private void addStudent() {
        System.out.println("\n═══════════════ ADD NEW STUDENT ═══════════════");
        
        int id = getIntInput("Enter Student ID: ");
        
        System.out.print("Enter Student Name: ");
        String name = scanner.nextLine();
        
        String email;
        while (true) {
            System.out.print("Enter Email: ");
            email = scanner.nextLine();
            if (InputValidator.isValidEmail(email)) {
                break;
            }
            System.out.println("✗ Invalid email format. Please try again.");
        }
        
        String phone;
        while (true) {
            System.out.print("Enter Phone (10 digits): ");
            phone = scanner.nextLine();
            if (InputValidator.isValidPhone(phone)) {
                break;
            }
            System.out.println("✗ Invalid phone number. Must be 10 digits.");
        }
        
        System.out.print("Enter Course: ");
        String course = scanner.nextLine();
        
        double gpa;
        while (true) {
            gpa = getDoubleInput("Enter GPA (0.0 - 4.0): ");
            if (InputValidator.isValidGPA(gpa)) {
                break;
            }
            System.out.println("✗ GPA must be between 0.0 and 4.0.");
        }
        
        Student student = new Student(id, name, email, phone, course, gpa);
        
        if (studentService.addStudent(student)) {
            System.out.println("\n✓ Student added successfully!");
        } else {
            System.out.println("\n✗ Failed to add student.");
        }
    }
    
    /**
     * View all students
     */
    private void viewAllStudents() {
        System.out.println("\n═══════════════ ALL STUDENTS ═══════════════");
        
        List<Student> students = studentService.getAllStudents();
        
        if (students.isEmpty()) {
            System.out.println("No students found in the system.");
        } else {
            System.out.println("\nTotal Students: " + students.size());
            System.out.println("─".repeat(140));
            for (Student student : students) {
                System.out.println(student);
            }
            System.out.println("─".repeat(140));
        }
    }
    
    /**
     * Search for students
     */
    private void searchStudent() {
        System.out.println("\n═══════════════ SEARCH STUDENT ═══════════════");
        System.out.println("1. Search by ID");
        System.out.println("2. Search by Name");
        System.out.println("3. Search by Course");
        
        int choice = getIntInput("Enter search option: ");
        
        switch (choice) {
            case 1:
                searchById();
                break;
            case 2:
                searchByName();
                break;
            case 3:
                searchByCourse();
                break;
            default:
                System.out.println("✗ Invalid option.");
        }
    }
    
    private void searchById() {
        int id = getIntInput("Enter Student ID: ");
        Student student = studentService.getStudentById(id);
        
        if (student != null) {
            System.out.println("\n✓ Student Found:");
            System.out.println("─".repeat(140));
            System.out.println(student);
            System.out.println("─".repeat(140));
        } else {
            System.out.println("\n✗ Student with ID " + id + " not found.");
        }
    }
    
    private void searchByName() {
        System.out.print("Enter name to search: ");
        String name = scanner.nextLine();
        
        List<Student> students = studentService.searchStudentsByName(name);
        displaySearchResults(students);
    }
    
    private void searchByCourse() {
        System.out.print("Enter course to search: ");
        String course = scanner.nextLine();
        
        List<Student> students = studentService.searchStudentsByCourse(course);
        displaySearchResults(students);
    }
    
    private void displaySearchResults(List<Student> students) {
        if (students.isEmpty()) {
            System.out.println("\n✗ No students found matching the criteria.");
        } else {
            System.out.println("\n✓ Found " + students.size() + " student(s):");
            System.out.println("─".repeat(140));
            for (Student student : students) {
                System.out.println(student);
            }
            System.out.println("─".repeat(140));
        }
    }
    
    /**
     * Update a student
     */
    private void updateStudent() {
        System.out.println("\n═══════════════ UPDATE STUDENT ═══════════════");
        
        int id = getIntInput("Enter Student ID to update: ");
        Student existing = studentService.getStudentById(id);
        
        if (existing == null) {
            System.out.println("\n✗ Student with ID " + id + " not found.");
            return;
        }
        
        System.out.println("\nCurrent Details:");
        System.out.println(existing);
        System.out.println("\nEnter new details (press Enter to keep current value):");
        
        System.out.print("Enter Name [" + existing.getName() + "]: ");
        String name = scanner.nextLine();
        if (name.trim().isEmpty()) name = existing.getName();
        
        System.out.print("Enter Email [" + existing.getEmail() + "]: ");
        String email = scanner.nextLine();
        if (email.trim().isEmpty()) {
            email = existing.getEmail();
        } else if (!InputValidator.isValidEmail(email)) {
            System.out.println("✗ Invalid email. Keeping existing value.");
            email = existing.getEmail();
        }
        
        System.out.print("Enter Phone [" + existing.getPhone() + "]: ");
        String phone = scanner.nextLine();
        if (phone.trim().isEmpty()) {
            phone = existing.getPhone();
        } else if (!InputValidator.isValidPhone(phone)) {
            System.out.println("✗ Invalid phone. Keeping existing value.");
            phone = existing.getPhone();
        }
        
        System.out.print("Enter Course [" + existing.getCourse() + "]: ");
        String course = scanner.nextLine();
        if (course.trim().isEmpty()) course = existing.getCourse();
        
        System.out.print("Enter GPA [" + existing.getGpa() + "]: ");
        String gpaStr = scanner.nextLine();
        double gpa = existing.getGpa();
        if (!gpaStr.trim().isEmpty()) {
            try {
                gpa = Double.parseDouble(gpaStr);
                if (!InputValidator.isValidGPA(gpa)) {
                    System.out.println("✗ Invalid GPA. Keeping existing value.");
                    gpa = existing.getGpa();
                }
            } catch (NumberFormatException e) {
                System.out.println("✗ Invalid number. Keeping existing value.");
            }
        }
        
        Student updated = new Student(id, name, email, phone, course, gpa);
        
        if (studentService.updateStudent(updated)) {
            System.out.println("\n✓ Student updated successfully!");
        } else {
            System.out.println("\n✗ Failed to update student.");
        }
    }
    
    /**
     * Delete a student
     */
    private void deleteStudent() {
        System.out.println("\n═══════════════ DELETE STUDENT ═══════════════");
        
        int id = getIntInput("Enter Student ID to delete: ");
        Student student = studentService.getStudentById(id);
        
        if (student == null) {
            System.out.println("\n✗ Student with ID " + id + " not found.");
            return;
        }
        
        System.out.println("\nStudent to delete:");
        System.out.println(student);
        
        System.out.print("\nAre you sure you want to delete this student? (yes/no): ");
        String confirmation = scanner.nextLine();
        
        if (confirmation.equalsIgnoreCase("yes") || confirmation.equalsIgnoreCase("y")) {
            if (studentService.deleteStudent(id)) {
                System.out.println("\n✓ Student deleted successfully!");
            } else {
                System.out.println("\n✗ Failed to delete student.");
            }
        } else {
            System.out.println("\n✓ Delete operation cancelled.");
        }
    }
    
    /**
     * Display statistics
     */
    private void displayStatistics() {
        System.out.println("\n═══════════════ STATISTICS ═══════════════");
        
        int total = studentService.getTotalStudents();
        double avgGPA = studentService.getAverageGPA();
        
        System.out.println("\n┌────────────────────────────────────────┐");
        System.out.printf("│ Total Students:           %-12d │\n", total);
        System.out.printf("│ Average GPA:              %-12.2f │\n", avgGPA);
        System.out.println("└────────────────────────────────────────┘");
    }
    
    /**
     * Get integer input from user
     */
    private int getIntInput(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                String input = scanner.nextLine();
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("✗ Invalid input. Please enter a number.");
            }
        }
    }
    
    /**
     * Get double input from user
     */
    private double getDoubleInput(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                String input = scanner.nextLine();
                return Double.parseDouble(input);
            } catch (NumberFormatException e) {
                System.out.println("✗ Invalid input. Please enter a valid number.");
            }
        }
    }
}
