package com.studentmgmt.service;

import com.studentmgmt.dao.StudentDAO;
import com.studentmgmt.dao.StudentDAOImpl;
import com.studentmgmt.model.Student;
import java.util.List;

/**
 * Service layer for student management business logic
 */
public class StudentService {
    
    private StudentDAO studentDAO;
    
    public StudentService() {
        this.studentDAO = new StudentDAOImpl();
    }
    
    /**
     * Add a new student with validation
     */
    public boolean addStudent(Student student) {
        // Validation
        if (student == null) {
            System.out.println("Error: Student object cannot be null.");
            return false;
        }
        
        if (student.getId() <= 0) {
            System.out.println("Error: Student ID must be positive.");
            return false;
        }
        
        if (student.getName() == null || student.getName().trim().isEmpty()) {
            System.out.println("Error: Student name cannot be empty.");
            return false;
        }
        
        if (student.getGpa() < 0.0 || student.getGpa() > 4.0) {
            System.out.println("Error: GPA must be between 0.0 and 4.0.");
            return false;
        }
        
        if (studentDAO.studentExists(student.getId())) {
            System.out.println("Error: Student with ID " + student.getId() + " already exists.");
            return false;
        }
        
        return studentDAO.addStudent(student);
    }
    
    /**
     * Update an existing student
     */
    public boolean updateStudent(Student student) {
        if (student == null) {
            System.out.println("Error: Student object cannot be null.");
            return false;
        }
        
        if (!studentDAO.studentExists(student.getId())) {
            System.out.println("Error: Student with ID " + student.getId() + " does not exist.");
            return false;
        }
        
        if (student.getGpa() < 0.0 || student.getGpa() > 4.0) {
            System.out.println("Error: GPA must be between 0.0 and 4.0.");
            return false;
        }
        
        return studentDAO.updateStudent(student);
    }
    
    /**
     * Delete a student by ID
     */
    public boolean deleteStudent(int id) {
        if (!studentDAO.studentExists(id)) {
            System.out.println("Error: Student with ID " + id + " does not exist.");
            return false;
        }
        
        return studentDAO.deleteStudent(id);
    }
    
    /**
     * Get a student by ID
     */
    public Student getStudentById(int id) {
        return studentDAO.getStudentById(id);
    }
    
    /**
     * Get all students
     */
    public List<Student> getAllStudents() {
        return studentDAO.getAllStudents();
    }
    
    /**
     * Search students by name
     */
    public List<Student> searchStudentsByName(String name) {
        if (name == null || name.trim().isEmpty()) {
            System.out.println("Error: Search name cannot be empty.");
            return List.of();
        }
        return studentDAO.searchStudentsByName(name);
    }
    
    /**
     * Search students by course
     */
    public List<Student> searchStudentsByCourse(String course) {
        if (course == null || course.trim().isEmpty()) {
            System.out.println("Error: Search course cannot be empty.");
            return List.of();
        }
        return studentDAO.searchStudentsByCourse(course);
    }
    
    /**
     * Get total number of students
     */
    public int getTotalStudents() {
        return studentDAO.getAllStudents().size();
    }
    
    /**
     * Calculate average GPA of all students
     */
    public double getAverageGPA() {
        List<Student> allStudents = studentDAO.getAllStudents();
        if (allStudents.isEmpty()) {
            return 0.0;
        }
        
        return allStudents.stream()
                .mapToDouble(Student::getGpa)
                .average()
                .orElse(0.0);
    }
}
