package com.studentmgmt.dao;

import com.studentmgmt.model.Student;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Implementation of StudentDAO using file-based persistence
 */
public class StudentDAOImpl implements StudentDAO {
    
    private static final String DATA_FILE = "students.dat";
    private List<Student> students;
    
    public StudentDAOImpl() {
        students = new ArrayList<>();
        loadStudents();
    }
    
    /**
     * Load students from file
     */
    @SuppressWarnings("unchecked")
    private void loadStudents() {
        File file = new File(DATA_FILE);
        if (file.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
                students = (List<Student>) ois.readObject();
                System.out.println("Loaded " + students.size() + " students from file.");
            } catch (IOException | ClassNotFoundException e) {
                System.err.println("Error loading students: " + e.getMessage());
                students = new ArrayList<>();
            }
        } else {
            System.out.println("No existing data file found. Starting with empty student list.");
        }
    }
    
    /**
     * Save students to file
     */
    private void saveStudents() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(DATA_FILE))) {
            oos.writeObject(students);
        } catch (IOException e) {
            System.err.println("Error saving students: " + e.getMessage());
        }
    }
    
    @Override
    public boolean addStudent(Student student) {
        if (studentExists(student.getId())) {
            return false;
        }
        students.add(student);
        saveStudents();
        return true;
    }
    
    @Override
    public boolean updateStudent(Student student) {
        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).getId() == student.getId()) {
                students.set(i, student);
                saveStudents();
                return true;
            }
        }
        return false;
    }
    
    @Override
    public boolean deleteStudent(int id) {
        boolean removed = students.removeIf(student -> student.getId() == id);
        if (removed) {
            saveStudents();
        }
        return removed;
    }
    
    @Override
    public Student getStudentById(int id) {
        return students.stream()
                .filter(student -> student.getId() == id)
                .findFirst()
                .orElse(null);
    }
    
    @Override
    public List<Student> getAllStudents() {
        return new ArrayList<>(students);
    }
    
    @Override
    public List<Student> searchStudentsByName(String name) {
        return students.stream()
                .filter(student -> student.getName().toLowerCase().contains(name.toLowerCase()))
                .collect(Collectors.toList());
    }
    
    @Override
    public List<Student> searchStudentsByCourse(String course) {
        return students.stream()
                .filter(student -> student.getCourse().equalsIgnoreCase(course))
                .collect(Collectors.toList());
    }
    
    @Override
    public boolean studentExists(int id) {
        return students.stream().anyMatch(student -> student.getId() == id);
    }
}
