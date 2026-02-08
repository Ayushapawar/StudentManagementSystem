# Student Management System

A comprehensive console-based Student Management System built in Java for Eclipse IDE.

## 📋 Features

- **Add Student**: Register new students with validation
- **View All Students**: Display all registered students
- **Search Students**: Search by ID, name, or course
- **Update Student**: Modify existing student information
- **Delete Student**: Remove students from the system
- **Statistics**: View total students and average GPA
- **Data Persistence**: Automatic file-based storage

## 🏗️ Project Structure

```
StudentManagementSystem/
├── src/
│   └── com/
│       └── studentmgmt/
│           ├── Main.java                    # Application entry point
│           ├── model/
│           │   └── Student.java             # Student entity class
│           ├── dao/
│           │   ├── StudentDAO.java          # DAO interface
│           │   └── StudentDAOImpl.java      # DAO implementation
│           ├── service/
│           │   └── StudentService.java      # Business logic layer
│           ├── ui/
│           │   └── ConsoleUI.java           # User interface
│           └── util/
│               └── InputValidator.java      # Input validation utilities
├── .project                                  # Eclipse project file
├── .classpath                                # Eclipse classpath
└── README.md                                 # This file
```

## 🚀 How to Import and Run in Eclipse IDE

### Method 1: Import Existing Project

1. **Extract the Project**
   - Extract the StudentManagementSystem folder to your desired location

2. **Open Eclipse IDE**
   - Launch Eclipse IDE
   - Select your workspace

3. **Import the Project**
   - Go to `File` → `Import`
   - Select `General` → `Existing Projects into Workspace`
   - Click `Next`
   - Click `Browse` and navigate to the StudentManagementSystem folder
   - Select the project and click `Finish`

4. **Run the Application**
   - Right-click on `Main.java` in the Project Explorer
   - Select `Run As` → `Java Application`

### Method 2: Create New Project and Copy Files

1. **Create New Java Project**
   - Go to `File` → `New` → `Java Project`
   - Name it "StudentManagementSystem"
   - Click `Finish`

2. **Copy Source Files**
   - Copy all files from the `src` folder into your new project's `src` folder

3. **Run the Application**
   - Right-click on `Main.java`
   - Select `Run As` → `Java Application`

## 💻 System Requirements

- **Java Version**: Java 11 or higher
- **IDE**: Eclipse IDE (2020-03 or later recommended)
- **Operating System**: Windows, macOS, or Linux

## 📖 Usage Guide

### Adding a Student

1. Select option `1` from the main menu
2. Enter student details:
   - Student ID (positive integer)
   - Name
   - Email (valid format: example@domain.com)
   - Phone (10 digits)
   - Course
   - GPA (0.0 - 4.0)

### Viewing All Students

- Select option `2` to display all registered students

### Searching Students

1. Select option `3`
2. Choose search criteria:
   - By ID
   - By Name (partial match supported)
   - By Course

### Updating a Student

1. Select option `4`
2. Enter the Student ID
3. Enter new values (press Enter to keep existing values)

### Deleting a Student

1. Select option `5`
2. Enter the Student ID
3. Confirm deletion

### Viewing Statistics

- Select option `6` to view:
  - Total number of students
  - Average GPA

## 🔒 Data Validation

The system includes comprehensive validation:

- **Student ID**: Must be a positive integer and unique
- **Name**: Cannot be empty
- **Email**: Must follow standard email format
- **Phone**: Must be exactly 10 digits
- **GPA**: Must be between 0.0 and 4.0
- **Course**: Cannot be empty

## 💾 Data Persistence

- Student data is automatically saved to `students.dat` file
- Data is loaded when the application starts
- Uses Java serialization for object persistence

## 🎯 Key Components

### Model Layer
- **Student**: Represents a student entity with all attributes

### DAO Layer
- **StudentDAO**: Interface defining data access operations
- **StudentDAOImpl**: File-based implementation with serialization

### Service Layer
- **StudentService**: Business logic and validation

### UI Layer
- **ConsoleUI**: Interactive console interface

### Utility Layer
- **InputValidator**: Email, phone, and GPA validation

## 🔧 Troubleshooting

### Common Issues

1. **"Class not found" error**
   - Ensure Java 11 or higher is installed
   - Check Eclipse build path configuration

2. **Data not persisting**
   - Check write permissions in the application directory
   - Ensure `students.dat` file is not corrupted

3. **Invalid input errors**
   - Follow the format requirements for each field
   - Email: user@domain.com
   - Phone: 10 digits only

## 🚀 Future Enhancements

Potential features for future versions:
- Database integration (MySQL/PostgreSQL)
- GUI using JavaFX or Swing
- Export data to CSV/Excel
- Attendance tracking
- Grade management
- Course enrollment system
- Multi-user authentication

## 👨‍💻 Author

Developed as a learning project for Java developers

## 📝 License

This project is open source and available for educational purposes.

## 🤝 Contributing

Feel free to fork this project and submit improvements!

---

**Happy Coding! 🎓**
