package com.studentmgmt.dao;

import com.studentmgmt.model.Student;
import java.util.List;

/**
 * Data Access Object interface for Student operations
 */
public interface StudentDAO {
    
    /**
     * Add a new student to the system
     * @param student Student object to add
     * @return true if added successfully, false otherwise
     */
    boolean addStudent(Student student);
    
    /**
     * Update an existing student
     * @param student Student object with updated information
     * @return true if updated successfully, false otherwise
     */
    boolean updateStudent(Student student);
    
    /**
     * Delete a student by ID
     * @param id Student ID to delete
     * @return true if deleted successfully, false otherwise
     */
    boolean deleteStudent(int id);
    
    /**
     * Get a student by ID
     * @param id Student ID to retrieve
     * @return Student object if found, null otherwise
     */
    Student getStudentById(int id);
    
    /**
     * Get all students in the system
     * @return List of all students
     */
    List<Student> getAllStudents();
    
    /**
     * Search students by name
     * @param name Name to search for (partial match)
     * @return List of matching students
     */
    List<Student> searchStudentsByName(String name);
    
    /**
     * Search students by course
     * @param course Course to search for
     * @return List of matching students
     */
    List<Student> searchStudentsByCourse(String course);
    
    /**
     * Check if a student ID exists
     * @param id Student ID to check
     * @return true if exists, false otherwise
     */
    boolean studentExists(int id);
}
